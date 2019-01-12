package com.client.event;

/**
 * 文 件 名: FragmentEvent
 * 创 建 人: 何庆
 * 创建日期: 2018/12/31 04:04
 * 修改备注：
 */

public class FragmentEvent {
    private int type;
    private String text;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private FragmentEvent(Builder builder) {
        type = builder.type;
        text = builder.text;
    }

    public static final class Builder {
        private int type;
        private String text;

        public Builder() {
        }

        public Builder type(int val) {
            type = val;
            return this;
        }

        public Builder text(String val) {
            text = val;
            return this;
        }

        public FragmentEvent build() {
            return new FragmentEvent(this);
        }
    }
}
