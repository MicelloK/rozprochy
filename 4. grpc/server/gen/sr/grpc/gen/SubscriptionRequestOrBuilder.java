// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: subscribeEvent.proto

// Protobuf Java Version: 4.26.1
package sr.grpc.gen;

public interface SubscriptionRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:event.subscription.SubscriptionRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
   * @return A list containing the eventTypes.
   */
  java.util.List<sr.grpc.gen.Event.EventType> getEventTypesList();
  /**
   * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
   * @return The count of eventTypes.
   */
  int getEventTypesCount();
  /**
   * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
   * @param index The index of the element to return.
   * @return The eventTypes at the given index.
   */
  sr.grpc.gen.Event.EventType getEventTypes(int index);
  /**
   * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
   * @return A list containing the enum numeric values on the wire for eventTypes.
   */
  java.util.List<java.lang.Integer>
  getEventTypesValueList();
  /**
   * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
   * @param index The index of the value to return.
   * @return The enum numeric value on the wire of eventTypes at the given index.
   */
  int getEventTypesValue(int index);
}
