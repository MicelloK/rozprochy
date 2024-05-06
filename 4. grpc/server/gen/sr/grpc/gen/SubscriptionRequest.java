// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: subscribeEvent.proto

// Protobuf Java Version: 4.26.1
package sr.grpc.gen;

/**
 * Protobuf type {@code event.subscription.SubscriptionRequest}
 */
public final class SubscriptionRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:event.subscription.SubscriptionRequest)
    SubscriptionRequestOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 1,
      /* suffix= */ "",
      SubscriptionRequest.class.getName());
  }
  // Use SubscriptionRequest.newBuilder() to construct.
  private SubscriptionRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private SubscriptionRequest() {
    eventTypes_ = java.util.Collections.emptyList();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sr.grpc.gen.EventSubscriptionProto.internal_static_event_subscription_SubscriptionRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sr.grpc.gen.EventSubscriptionProto.internal_static_event_subscription_SubscriptionRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sr.grpc.gen.SubscriptionRequest.class, sr.grpc.gen.SubscriptionRequest.Builder.class);
  }

  public static final int EVENTTYPES_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private java.util.List<java.lang.Integer> eventTypes_;
  private static final com.google.protobuf.Internal.ListAdapter.Converter<
      java.lang.Integer, sr.grpc.gen.Event.EventType> eventTypes_converter_ =
          new com.google.protobuf.Internal.ListAdapter.Converter<
              java.lang.Integer, sr.grpc.gen.Event.EventType>() {
            public sr.grpc.gen.Event.EventType convert(java.lang.Integer from) {
              sr.grpc.gen.Event.EventType result = sr.grpc.gen.Event.EventType.forNumber(from);
              return result == null ? sr.grpc.gen.Event.EventType.UNRECOGNIZED : result;
            }
          };
  /**
   * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
   * @return A list containing the eventTypes.
   */
  @java.lang.Override
  public java.util.List<sr.grpc.gen.Event.EventType> getEventTypesList() {
    return new com.google.protobuf.Internal.ListAdapter<
        java.lang.Integer, sr.grpc.gen.Event.EventType>(eventTypes_, eventTypes_converter_);
  }
  /**
   * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
   * @return The count of eventTypes.
   */
  @java.lang.Override
  public int getEventTypesCount() {
    return eventTypes_.size();
  }
  /**
   * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
   * @param index The index of the element to return.
   * @return The eventTypes at the given index.
   */
  @java.lang.Override
  public sr.grpc.gen.Event.EventType getEventTypes(int index) {
    return eventTypes_converter_.convert(eventTypes_.get(index));
  }
  /**
   * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
   * @return A list containing the enum numeric values on the wire for eventTypes.
   */
  @java.lang.Override
  public java.util.List<java.lang.Integer>
  getEventTypesValueList() {
    return eventTypes_;
  }
  /**
   * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
   * @param index The index of the value to return.
   * @return The enum numeric value on the wire of eventTypes at the given index.
   */
  @java.lang.Override
  public int getEventTypesValue(int index) {
    return eventTypes_.get(index);
  }
  private int eventTypesMemoizedSerializedSize;

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    getSerializedSize();
    if (getEventTypesList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(eventTypesMemoizedSerializedSize);
    }
    for (int i = 0; i < eventTypes_.size(); i++) {
      output.writeEnumNoTag(eventTypes_.get(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < eventTypes_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeEnumSizeNoTag(eventTypes_.get(i));
      }
      size += dataSize;
      if (!getEventTypesList().isEmpty()) {  size += 1;
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32SizeNoTag(dataSize);
      }eventTypesMemoizedSerializedSize = dataSize;
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof sr.grpc.gen.SubscriptionRequest)) {
      return super.equals(obj);
    }
    sr.grpc.gen.SubscriptionRequest other = (sr.grpc.gen.SubscriptionRequest) obj;

    if (!eventTypes_.equals(other.eventTypes_)) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getEventTypesCount() > 0) {
      hash = (37 * hash) + EVENTTYPES_FIELD_NUMBER;
      hash = (53 * hash) + eventTypes_.hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sr.grpc.gen.SubscriptionRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.SubscriptionRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.SubscriptionRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.SubscriptionRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.SubscriptionRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.SubscriptionRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.SubscriptionRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.SubscriptionRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static sr.grpc.gen.SubscriptionRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static sr.grpc.gen.SubscriptionRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sr.grpc.gen.SubscriptionRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.SubscriptionRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(sr.grpc.gen.SubscriptionRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code event.subscription.SubscriptionRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:event.subscription.SubscriptionRequest)
      sr.grpc.gen.SubscriptionRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sr.grpc.gen.EventSubscriptionProto.internal_static_event_subscription_SubscriptionRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sr.grpc.gen.EventSubscriptionProto.internal_static_event_subscription_SubscriptionRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sr.grpc.gen.SubscriptionRequest.class, sr.grpc.gen.SubscriptionRequest.Builder.class);
    }

    // Construct using sr.grpc.gen.SubscriptionRequest.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      eventTypes_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sr.grpc.gen.EventSubscriptionProto.internal_static_event_subscription_SubscriptionRequest_descriptor;
    }

    @java.lang.Override
    public sr.grpc.gen.SubscriptionRequest getDefaultInstanceForType() {
      return sr.grpc.gen.SubscriptionRequest.getDefaultInstance();
    }

    @java.lang.Override
    public sr.grpc.gen.SubscriptionRequest build() {
      sr.grpc.gen.SubscriptionRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sr.grpc.gen.SubscriptionRequest buildPartial() {
      sr.grpc.gen.SubscriptionRequest result = new sr.grpc.gen.SubscriptionRequest(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(sr.grpc.gen.SubscriptionRequest result) {
      if (((bitField0_ & 0x00000001) != 0)) {
        eventTypes_ = java.util.Collections.unmodifiableList(eventTypes_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.eventTypes_ = eventTypes_;
    }

    private void buildPartial0(sr.grpc.gen.SubscriptionRequest result) {
      int from_bitField0_ = bitField0_;
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof sr.grpc.gen.SubscriptionRequest) {
        return mergeFrom((sr.grpc.gen.SubscriptionRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sr.grpc.gen.SubscriptionRequest other) {
      if (other == sr.grpc.gen.SubscriptionRequest.getDefaultInstance()) return this;
      if (!other.eventTypes_.isEmpty()) {
        if (eventTypes_.isEmpty()) {
          eventTypes_ = other.eventTypes_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureEventTypesIsMutable();
          eventTypes_.addAll(other.eventTypes_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              int tmpRaw = input.readEnum();
              ensureEventTypesIsMutable();
              eventTypes_.add(tmpRaw);
              break;
            } // case 8
            case 10: {
              int length = input.readRawVarint32();
              int oldLimit = input.pushLimit(length);
              while(input.getBytesUntilLimit() > 0) {
                int tmpRaw = input.readEnum();
                ensureEventTypesIsMutable();
                eventTypes_.add(tmpRaw);
              }
              input.popLimit(oldLimit);
              break;
            } // case 10
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.util.List<java.lang.Integer> eventTypes_ =
      java.util.Collections.emptyList();
    private void ensureEventTypesIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        eventTypes_ = new java.util.ArrayList<java.lang.Integer>(eventTypes_);
        bitField0_ |= 0x00000001;
      }
    }
    /**
     * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
     * @return A list containing the eventTypes.
     */
    public java.util.List<sr.grpc.gen.Event.EventType> getEventTypesList() {
      return new com.google.protobuf.Internal.ListAdapter<
          java.lang.Integer, sr.grpc.gen.Event.EventType>(eventTypes_, eventTypes_converter_);
    }
    /**
     * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
     * @return The count of eventTypes.
     */
    public int getEventTypesCount() {
      return eventTypes_.size();
    }
    /**
     * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
     * @param index The index of the element to return.
     * @return The eventTypes at the given index.
     */
    public sr.grpc.gen.Event.EventType getEventTypes(int index) {
      return eventTypes_converter_.convert(eventTypes_.get(index));
    }
    /**
     * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
     * @param index The index to set the value at.
     * @param value The eventTypes to set.
     * @return This builder for chaining.
     */
    public Builder setEventTypes(
        int index, sr.grpc.gen.Event.EventType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureEventTypesIsMutable();
      eventTypes_.set(index, value.getNumber());
      onChanged();
      return this;
    }
    /**
     * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
     * @param value The eventTypes to add.
     * @return This builder for chaining.
     */
    public Builder addEventTypes(sr.grpc.gen.Event.EventType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureEventTypesIsMutable();
      eventTypes_.add(value.getNumber());
      onChanged();
      return this;
    }
    /**
     * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
     * @param values The eventTypes to add.
     * @return This builder for chaining.
     */
    public Builder addAllEventTypes(
        java.lang.Iterable<? extends sr.grpc.gen.Event.EventType> values) {
      ensureEventTypesIsMutable();
      for (sr.grpc.gen.Event.EventType value : values) {
        eventTypes_.add(value.getNumber());
      }
      onChanged();
      return this;
    }
    /**
     * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearEventTypes() {
      eventTypes_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
     * @return A list containing the enum numeric values on the wire for eventTypes.
     */
    public java.util.List<java.lang.Integer>
    getEventTypesValueList() {
      return java.util.Collections.unmodifiableList(eventTypes_);
    }
    /**
     * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
     * @param index The index of the value to return.
     * @return The enum numeric value on the wire of eventTypes at the given index.
     */
    public int getEventTypesValue(int index) {
      return eventTypes_.get(index);
    }
    /**
     * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
     * @param index The index to set the value at.
     * @param value The enum numeric value on the wire for eventTypes to set.
     * @return This builder for chaining.
     */
    public Builder setEventTypesValue(
        int index, int value) {
      ensureEventTypesIsMutable();
      eventTypes_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
     * @param value The enum numeric value on the wire for eventTypes to add.
     * @return This builder for chaining.
     */
    public Builder addEventTypesValue(int value) {
      ensureEventTypesIsMutable();
      eventTypes_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated .event.subscription.Event.EventType eventTypes = 1;</code>
     * @param values The enum numeric values on the wire for eventTypes to add.
     * @return This builder for chaining.
     */
    public Builder addAllEventTypesValue(
        java.lang.Iterable<java.lang.Integer> values) {
      ensureEventTypesIsMutable();
      for (int value : values) {
        eventTypes_.add(value);
      }
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:event.subscription.SubscriptionRequest)
  }

  // @@protoc_insertion_point(class_scope:event.subscription.SubscriptionRequest)
  private static final sr.grpc.gen.SubscriptionRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sr.grpc.gen.SubscriptionRequest();
  }

  public static sr.grpc.gen.SubscriptionRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SubscriptionRequest>
      PARSER = new com.google.protobuf.AbstractParser<SubscriptionRequest>() {
    @java.lang.Override
    public SubscriptionRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<SubscriptionRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SubscriptionRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sr.grpc.gen.SubscriptionRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

