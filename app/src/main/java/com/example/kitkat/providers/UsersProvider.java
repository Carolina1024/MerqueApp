package com.example.kitkat.providers;

import com.example.kitkat.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UsersProvider {

    private CollectionReference mcollection;

    public UsersProvider() {
        mcollection=FirebaseFirestore.getInstance().collection("Users");

    }
    public Task<DocumentSnapshot> getUser(String id){
        return mcollection.document(id).get();
    }
    public Task<Void> create(User user){
        return mcollection.document(user.getId()).set(user);
    }


    public Task<Void> update(User user){
        Map<String,Object>map=new HashMap<>();
        map.put("username",user.getUsername());
        //map.put("email",user.getEmail());
        //map.put("password",user.getPassword());
        return mcollection.document(user.getId()).update(map);
    }
}
