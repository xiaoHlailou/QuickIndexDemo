package com.example.quickindexdemo.bean;

import com.example.quickindexdemo.utils.PinYinUtils;

public class Friend implements Comparable<Friend> {
	private String name;
	private String pinyin;

	public Friend(String name) {
		super();
		this.name = name;
		
		//一开始就转好拼音
		setPinyin(PinYinUtils.getPinYin(name));
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Friend another) {
//		String pinyin = PinYinUtils.getPinYin(name);
//		String anotherPinyin = PinYinUtils.getPinYin(another.getName());
//		return pinyin.compareTo(anotherPinyin);
		
		return getPinyin().compareTo(another.getPinyin());
	}

}
