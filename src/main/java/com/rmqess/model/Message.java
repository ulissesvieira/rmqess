
package com.rmqess.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "time_sent",
    "sender_id",
    "addressee_id",
    "topic",
    "subject",
    "content"
})
public class Message {

    @JsonProperty("time_sent")
    private Long timeSent;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("sender_id")
    private Long senderId;
    @JsonProperty("addressee_id")
    private Long addresseeId;
    @JsonProperty("topic")
    private String topic;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("subject")
    private String subject;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("content")
    private String content;

    @JsonProperty("time_sent")
    public Long getTimeSent() {
        return timeSent;
    }

    @JsonProperty("time_sent")
    public void setTimeSent(Long timeSent) {
        this.timeSent = timeSent;
    }

    public Message withTimeSent(Long timeSent) {
        this.timeSent = timeSent;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("sender_id")
    public Long getSenderId() {
        return senderId;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("sender_id")
    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Message withSenderId(Long senderId) {
        this.senderId = senderId;
        return this;
    }

    @JsonProperty("addressee_id")
    public Long getAddresseeId() {
        return addresseeId;
    }

    @JsonProperty("addressee_id")
    public void setAddresseeId(Long addresseeId) {
        this.addresseeId = addresseeId;
    }

    public Message withAddresseeId(Long addresseeId) {
        this.addresseeId = addresseeId;
        return this;
    }

    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Message withTopic(String topic) {
        this.topic = topic;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Message withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    public Message withContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Message.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("timeSent");
        sb.append('=');
        sb.append(((this.timeSent == null)?"<null>":this.timeSent));
        sb.append(',');
        sb.append("senderId");
        sb.append('=');
        sb.append(((this.senderId == null)?"<null>":this.senderId));
        sb.append(',');
        sb.append("addresseeId");
        sb.append('=');
        sb.append(((this.addresseeId == null)?"<null>":this.addresseeId));
        sb.append(',');
        sb.append("topic");
        sb.append('=');
        sb.append(((this.topic == null)?"<null>":this.topic));
        sb.append(',');
        sb.append("subject");
        sb.append('=');
        sb.append(((this.subject == null)?"<null>":this.subject));
        sb.append(',');
        sb.append("content");
        sb.append('=');
        sb.append(((this.content == null)?"<null>":this.content));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.addresseeId == null)? 0 :this.addresseeId.hashCode()));
        result = ((result* 31)+((this.senderId == null)? 0 :this.senderId.hashCode()));
        result = ((result* 31)+((this.subject == null)? 0 :this.subject.hashCode()));
        result = ((result* 31)+((this.timeSent == null)? 0 :this.timeSent.hashCode()));
        result = ((result* 31)+((this.topic == null)? 0 :this.topic.hashCode()));
        result = ((result* 31)+((this.content == null)? 0 :this.content.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Message) == false) {
            return false;
        }
        Message rhs = ((Message) other);
        return (((((((this.addresseeId == rhs.addresseeId)||((this.addresseeId!= null)&&this.addresseeId.equals(rhs.addresseeId)))&&((this.senderId == rhs.senderId)||((this.senderId!= null)&&this.senderId.equals(rhs.senderId))))&&((this.subject == rhs.subject)||((this.subject!= null)&&this.subject.equals(rhs.subject))))&&((this.timeSent == rhs.timeSent)||((this.timeSent!= null)&&this.timeSent.equals(rhs.timeSent))))&&((this.topic == rhs.topic)||((this.topic!= null)&&this.topic.equals(rhs.topic))))&&((this.content == rhs.content)||((this.content!= null)&&this.content.equals(rhs.content))));
    }

}
