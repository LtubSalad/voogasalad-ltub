package user.serializabledata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import user.User;
import user.UsersModel;

public class SerializableUserModel {
	
	
	private Map<String, String> userToPass; 
	private Map<String, SerializableUser> usernameToData; 
	private List<String> passwordVerify; 

	public SerializableUserModel(UsersModel usersModel) {
		userToPass = usersModel.getUserPasswordData();
		usernameToData = makeSerializable(usersModel.getUserData());
		passwordVerify = usersModel.getVerificationStructure();
	}

	private Map<String, SerializableUser> makeSerializable(Map<String, User> userData) {
		Map<String, SerializableUser> serializeMe = new HashMap<String, SerializableUser>();
		userData.keySet().stream().forEach(name -> {
			SerializableUser newUser = new SerializableUser(userData.get(name));
			serializeMe.put(name, newUser);	
		});
		return serializeMe; 
	}

	//TODO: extraction methods!!!

	
}
