package com.example.quickindexdemo.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.text.TextUtils;

//汉字转拼音  借助pinyin4j.jar实现
/**
 * 获取汉字的拼音，会销毁一定的资源，所以不应该被频繁调用,修改Friend构造方法，在构造方法里直接先获取结果
 * @author hjz
 */
public class PinYinUtils {
	public static String getPinYin(String chinese) {
		if (TextUtils.isEmpty(chinese)) {
			return null;
		}

		// 用来设置转换的拼音的大小写，或者声调
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 设置转换的拼音为大写字母
		// format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);//黑马->HEI1MA3
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		// 1.由于只能对单个汉字转换,需要将字符串转换为字符数组，然后对每个字符转换，最后拼接起来
		char[] charArray = chinese.toCharArray();
		String pinyin = "";
		for (int i = 0; i < charArray.length; i++) {
			// 2.过滤空格 黑 马->HEIMA
			if (Character.isWhitespace(charArray[i]))
				continue;

			// 3.需要判断是否是汉字 @#$黑马
			// 汉字占2个字节，一个字节范围是-128~127,那么汉字>127
			if (charArray[i] > 127) {// 可能是汉字 jar包转换
				try {
					// 因为多音字的存在 所以返回数组
					String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(
							charArray[i], format);
					if (pinyinArr != null) {
						pinyin += pinyinArr[0];// 此处尽管有多音字，只能取第一个
					} else {// 没有找到对应的拼音,汉字有问题，或者可能不是汉字，忽略
					}

				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
					// 说明转化失败，不是汉字，比如(￣▽￣)",那么则忽略
				}
			} else {// 肯定不是汉字 应该是键盘上能输入的字符,这些字符能够排序，但不能获取拼音
					// 所以可以直接拼接
				pinyin += charArray[i];
			}
		}
		return pinyin;
	}
}
