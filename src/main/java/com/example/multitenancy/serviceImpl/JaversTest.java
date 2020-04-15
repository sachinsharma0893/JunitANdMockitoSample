package com.example.multitenancy.serviceImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.ListCompareAlgorithm;

import com.example.multitenancy.domain.Address;
import com.example.multitenancy.domain.Employee;
import com.example.multitenancy.domain.Role;
import com.example.multitenancy.domain.User;
import com.example.multitenancy.dto.AuditChangesDTO;
import com.example.multitenancy.enums.EmployeeEnum;
import com.example.multitenancy.enums.RoleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JaversTest {

	private Javers javers = JaversBuilder.javers().withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE)
			.build();

	public void getDifference() throws Exception {
		Role adminRole = new Role(1l, RoleEnum.ADMIN, "Admin");
		Role leadRole = new Role(2l, RoleEnum.LEAD, "Lead");
		Role reviewerRole = new Role(3l, RoleEnum.REVIEWER, "Reviewer");
		Set<Role> roleSet = new HashSet<Role>();

		roleSet.add(adminRole);
		roleSet.add(leadRole);
		roleSet.add(reviewerRole);

		User user1 = new User(1l, "userName1", "password", Boolean.TRUE, roleSet);
		User user2 = new User(2l, "userName2", "password", Boolean.TRUE, roleSet);
		User user3 = new User(3l, "userName3", "password", Boolean.TRUE, roleSet);

		Set<User> userSet = new HashSet<User>();
		userSet.add(user1);
		userSet.add(user2);
		userSet.add(user3);

		Address address = new Address(1l, "city1", "street1", userSet);
		Address address2 = new Address(2l, "city2", "street2", userSet);
		Address address3 = new Address(3l, "city3", "street3", userSet);

		Set<Address> addressSet = new HashSet<Address>();
		addressSet.add(address);
		addressSet.add(address2);
		addressSet.add(address3);

		Address address1 = new Address(4l, "city4", "street4", userSet);
		Address address21 = new Address(5l, "city5", "street5", userSet);
		Address address31 = new Address(6l, "city6", "street6", userSet);
		Address address41 = new Address(6l, "city6", "street6", userSet);

		Employee existingEmployee = new Employee(1l, "employeeOldName", "oldPosition", 0, 50, addressSet);
//		existingEmployee.getPrimaryAddress().clear();

		Set<Address> addressSetnew = new HashSet<Address>();
		addressSetnew.add(address1);
		addressSetnew.add(address21);
		addressSetnew.add(address31);
		addressSetnew.add(address41);

		Employee newEmployee = new Employee(1l, "employeeNewName", "newPosition", 0, 50, addressSetnew);

		Diff diff = javers.compare(existingEmployee, newEmployee);
//		System.out.println("diff******************** " + javers.getJsonConverter().toJson(diff));

		JsonArray jsonArray = javers.getJsonConverter().toJsonElement(diff.groupByObject()).getAsJsonArray().get(0)
				.getAsJsonObject().get("changes").getAsJsonArray();

		Iterator<JsonElement> it = jsonArray.iterator();
		while (it.hasNext()) {
			JsonObject jsonElement = (JsonObject) it.next();
			parseChangeTypes(jsonElement.get("changeType").getAsString(), jsonElement, existingEmployee);
		}
	}

	public void parseChangeTypes(String changeType, JsonObject object, Object classObject) throws Exception {
		AuditChangesDTO finalObject = new AuditChangesDTO();

		switch (changeType) {
		case "NewObject": {
			JsonObject jsonObject = object.get("globalId").getAsJsonObject();
			String property = jsonObject.get("fragment").getAsString().split("\\/")[0];
			getValues(classObject, property, finalObject);
			System.out.println(" New Object is " + EmployeeEnum.getEmployeeEnum(property).getEntityProperty());
			break;
		}
		case "ObjectRemoved": {
			JsonObject jsonObject = object.get("globalId").getAsJsonObject();
			String property = jsonObject.get("fragment").getAsString().split("\\/")[0];
			System.out.println(" Object Removed " + EmployeeEnum.getEmployeeEnum(property).getEntityProperty());
			break;
		}
		case "ValueChange": {
			String propertName = object.get("property").getAsString();
			String oldProperty = object.get("left").getAsString();
			String newProperty = object.get("right").getAsString();
			System.out.println(propertName + " Changed from " + oldProperty + " to " + newProperty);
			break;
		}
		default:
			System.out.println("Default Executed ");
			break;
		}
	}

	public String getValues(Object obj, String keywordsString, AuditChangesDTO finalObject) throws Exception {
		String entityProperty = keywordsString;
		Method getter = null;
		PropertyDescriptor descriptor = new PropertyDescriptor(entityProperty, obj.getClass());
		Class<?> classType = descriptor.getPropertyType();
		getter = descriptor.getReadMethod();
		Object getterValue = getter.invoke(obj);
		Field field = obj.getClass().getDeclaredField(keywordsString);
		Type type = field.getGenericType();
		if (classType.getName().equals(Set.class.getName())) {
			ParameterizedType pt = (ParameterizedType) type;
			for (Type t : pt.getActualTypeArguments()) {
				System.out.println(t);
				if(t.equals(Address.class)) {
					Address address = (Address) t;
					System.out.println(address.getId());

				}
				
				
			}
		} else if (classType.getName().equals(String.class.getName())) {
			keywordsString = String.valueOf(getterValue);
		}
		return keywordsString;
	}

	public static void main(String args[]) throws Exception {
		JaversTest jTest = new JaversTest();
		jTest.getDifference();
//		System.out.println(EmployeeEnum.getEmployeeEnum("primaryAddress").getEntityProperty());
	}
}
