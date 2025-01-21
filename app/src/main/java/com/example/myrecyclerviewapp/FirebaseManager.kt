package com.example.myrecyclerviewapp

import com.google.firebase.database.*
import com.example.myrecyclerviewapp.models.Post

class FirebaseManager {
    private val database = FirebaseDatabase.getInstance()
    private val postRef = database.getReference("posts")

    fun fetchPosts(callback: (List<Post>) -> Unit) {
        postRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val postList = mutableListOf<Post>()
                snapshot.children.forEach {
                    val post = it.getValue(Post::class.java)
                    if (post != null) postList.add(post)
                }
                callback(postList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
    }
}
