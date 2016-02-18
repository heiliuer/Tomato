package com.tomato.timer;

public class TestGeneric {

	public <T> T getData() {
		return handlerData();
	}

	@SuppressWarnings("unchecked")
    public <T> T handlerData() {
		try {
			return (T) new Data<T>() {
			}.getTypeClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String data = new TestGeneric().getData();
		System.out.println(data);
	}
}
