package sr.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: subscribeEvent.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EventNotificationGrpc {

  private EventNotificationGrpc() {}

  public static final java.lang.String SERVICE_NAME = "event.subscription.EventNotification";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.SubscriptionRequest,
      sr.grpc.gen.Event> getSubscribeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Subscribe",
      requestType = sr.grpc.gen.SubscriptionRequest.class,
      responseType = sr.grpc.gen.Event.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.SubscriptionRequest,
      sr.grpc.gen.Event> getSubscribeMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.SubscriptionRequest, sr.grpc.gen.Event> getSubscribeMethod;
    if ((getSubscribeMethod = EventNotificationGrpc.getSubscribeMethod) == null) {
      synchronized (EventNotificationGrpc.class) {
        if ((getSubscribeMethod = EventNotificationGrpc.getSubscribeMethod) == null) {
          EventNotificationGrpc.getSubscribeMethod = getSubscribeMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.SubscriptionRequest, sr.grpc.gen.Event>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Subscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.SubscriptionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Event.getDefaultInstance()))
              .setSchemaDescriptor(new EventNotificationMethodDescriptorSupplier("Subscribe"))
              .build();
        }
      }
    }
    return getSubscribeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EventNotificationStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventNotificationStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventNotificationStub>() {
        @java.lang.Override
        public EventNotificationStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventNotificationStub(channel, callOptions);
        }
      };
    return EventNotificationStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EventNotificationBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventNotificationBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventNotificationBlockingStub>() {
        @java.lang.Override
        public EventNotificationBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventNotificationBlockingStub(channel, callOptions);
        }
      };
    return EventNotificationBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EventNotificationFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventNotificationFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventNotificationFutureStub>() {
        @java.lang.Override
        public EventNotificationFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventNotificationFutureStub(channel, callOptions);
        }
      };
    return EventNotificationFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void subscribe(sr.grpc.gen.SubscriptionRequest request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Event> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubscribeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EventNotification.
   */
  public static abstract class EventNotificationImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EventNotificationGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service EventNotification.
   */
  public static final class EventNotificationStub
      extends io.grpc.stub.AbstractAsyncStub<EventNotificationStub> {
    private EventNotificationStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventNotificationStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventNotificationStub(channel, callOptions);
    }

    /**
     */
    public void subscribe(sr.grpc.gen.SubscriptionRequest request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Event> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EventNotification.
   */
  public static final class EventNotificationBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EventNotificationBlockingStub> {
    private EventNotificationBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventNotificationBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventNotificationBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<sr.grpc.gen.Event> subscribe(
        sr.grpc.gen.SubscriptionRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSubscribeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service EventNotification.
   */
  public static final class EventNotificationFutureStub
      extends io.grpc.stub.AbstractFutureStub<EventNotificationFutureStub> {
    private EventNotificationFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventNotificationFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventNotificationFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SUBSCRIBE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBSCRIBE:
          serviceImpl.subscribe((sr.grpc.gen.SubscriptionRequest) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.Event>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSubscribeMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              sr.grpc.gen.SubscriptionRequest,
              sr.grpc.gen.Event>(
                service, METHODID_SUBSCRIBE)))
        .build();
  }

  private static abstract class EventNotificationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EventNotificationBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.grpc.gen.EventSubscriptionProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EventNotification");
    }
  }

  private static final class EventNotificationFileDescriptorSupplier
      extends EventNotificationBaseDescriptorSupplier {
    EventNotificationFileDescriptorSupplier() {}
  }

  private static final class EventNotificationMethodDescriptorSupplier
      extends EventNotificationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    EventNotificationMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EventNotificationGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EventNotificationFileDescriptorSupplier())
              .addMethod(getSubscribeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
