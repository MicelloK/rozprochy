import ray
import random
import os

NUM_STORAGE_NODES = 4
NUM_COPIES = 2
CHUNK_SIZE = 10

@ray.remote
class StorageNode:
     def __init__(self) -> None:
          self.chunks = {}
     
     def store_chunk(self, artifact_name, chunk_id, chunk):
          if artifact_name not in self.chunks:
               self.chunks.update({artifact_name : {}})
          
          artifact_chunks = self.chunks.get(artifact_name)
          artifact_chunks.update({chunk_id : chunk})
          
     def update_chunk(self, artifact_name, chunk_id, new_chunk):
          if artifact_name not in self.chunks:
               print("Can not update non existing artifact!")
               return -1
          if chunk_id not in self.chunks[artifact_name]:
               print("That node does not storage this chunk!")
               return -1
          
          artifact_chunks = self.chunks.get(artifact_name)
          artifact_chunks[chunk_id] = new_chunk
          
     def delete_chunk(self, artifact_name, chunk_id):
          if artifact_name in self.chunks:
               artifact_chunks = self.chunks.get(artifact_name)
               artifact_chunks.pop(chunk_id)
               
     def delete_artifact(self, artifact_name):
          if artifact_name in self.chunks:
               self.chunks.pop(artifact_name)
               
     def get_chunk(self, artifact_name, chunk_id):
          if artifact_name in self.chunks:
               return self.chunks[artifact_name][chunk_id]
          return None

     def list_chunks(self):
          return (os.getpid(), self.chunks)
          
          

class NameNode:
     def __init__(self) -> None:
          self.storage_nodes = {storage_id : StorageNode.remote() for storage_id in range(NUM_STORAGE_NODES)}
          self.artifacts = {} # key: artifactName, value: {key: chunkID, value: [storageID]}
          
     def upload(self, name, content):
          if name in self.artifacts:
               print("Can not upload existed element!")
               return -1
          
          self.artifacts[name] = {}

          chunks = self.split_content(content)
          for chunk_id, chunk in enumerate(chunks):
               self.artifacts[name][chunk_id] = []
               random_storage_nodes = random.sample(sorted(self.storage_nodes.keys()), NUM_COPIES)
               for storage_id in random_storage_nodes:
                    storage_node = self.storage_nodes.get(storage_id)
                    storage_node.store_chunk.remote(name, chunk_id, chunk)
                    self.artifacts[name][chunk_id].append(storage_id)
                    
     def update(self, name, content):
          if name not in self.artifacts:
               print("Can not update non existed artifact!")
               return -1
          
          artifact = self.artifacts[name]
          
          chunks = self.split_content(content)
          for chunk_id, chunk in enumerate(chunks):
               if chunk_id not in artifact:
                    artifact[chunk_id] = []
                    random_storage_nodes = random.sample(sorted(self.storage_nodes.keys()), NUM_COPIES)
                    for storage_id in random_storage_nodes:
                         storage_node = self.storage_nodes.get(storage_id)
                         storage_node.store_chunk.remote(name, chunk_id, chunk)
                         self.artifacts[name][chunk_id].append(storage_id)
               else:
                    for storage_node_id in artifact[chunk_id]:
                         storage_node = self.storage_nodes[storage_node_id]
                         storage_node.update_chunk.remote(name, chunk_id, chunk)
          
          
          chunks_to_remove = []
          for chunk_id in artifact:
               if chunk_id >= len(chunks):
                    chunks_to_remove.append(chunk_id)
                    
          for chunk_id in chunks_to_remove:
               for storage_node_id in artifact[chunk_id]:
                    storage_node = self.storage_nodes[storage_node_id]
                    storage_node.delete_chunk.remote(name, chunk_id)
               artifact.pop(chunk_id)
               
     def delete(self, name):
          for storage_node in self.storage_nodes.values():
               storage_node.delete_artifact.remote(name)
          if name in self.artifacts:
               self.artifacts.pop(name)
               
     def get(self, name):
          if name in self.artifacts:
               artifact = self.artifacts[name]
               result = ""
               for chunk_id, storages in artifact.items():
                    storage_node = self.storage_nodes[storages[0]]
                    result += ray.get(storage_node.get_chunk.remote(name, chunk_id))
               print(f"{name} | {result}")
          
     def split_content(self, content):
          return [content[i:i+CHUNK_SIZE] for i in range(0, len(content), CHUNK_SIZE)]

     def list_status(self):
          result = []
          for storage_node in self.storage_nodes.values():
               result.append(storage_node.list_chunks.remote())
          result = ray.get(result)
          
          for node in result:
               print(f"Node PID: {node[0]} | {node[1]}")
               
     def list_cluster_status(self, storage_id):
          if storage_id in self.storage_nodes:
               result = ray.get(self.storage_nodes[storage_id].list_chunks.remote())
               print(f"Node PID: {result[0]} | {result[1]}")
               
     def list_artifacts(self):
          for artifact_name, chunks in self.artifacts.items():
               print(f"{artifact_name} : {chunks}")
               
if __name__ == "__main__":
     ray.shutdown()
     ray.init()
     
     name_node = NameNode()
     
     exit_flag = False
     while not exit_flag:
          input_text = input('> ')
          if len(input_text) < 1:
               continue
          
          parsed_input = input_text.split(' ')
          command = parsed_input[0]
          
          if command == 'upload':
               if len(parsed_input) >= 3:
                    name_node.upload(parsed_input[1], " ".join(parsed_input[2:]))
          elif command == 'update':
               if len(parsed_input) >= 3:
                    name_node.update(parsed_input[1], " ".join(parsed_input[2:]))
          elif command == 'delete':
               if len(parsed_input) >= 2:
                    name_node.delete(parsed_input[1])
          elif command == 'get':
               if len(parsed_input) >= 2:
                    name_node.get(parsed_input[1])
          elif command == 'list':
               name_node.list_status()
          elif command == 'artifacts':
               name_node.list_artifacts()
          else:
               print("Unknown command")
                     
               