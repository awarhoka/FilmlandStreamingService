package com.filmland.streaming.util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

public class DozerBeanMapperExt extends DozerBeanMapper {

	public DozerBeanMapperExt() {
		super();
	}
	public <T, S> List<T> mapListObjectToNewListObject(List<S> srcObjects, Class<T> newObjectClass) {
		final List<T> newObjects = new ArrayList<T>();
		for (S srcObj : srcObjects) {
			newObjects.add(super.map(srcObj, newObjectClass));
		}
		return newObjects;
	}
}
