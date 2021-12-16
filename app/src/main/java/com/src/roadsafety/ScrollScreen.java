/**
 * Copyright Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.src.roadsafety;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.src.roadsafety.databinding.MirosViewBinding;
import com.src.roadsafety.databinding.ScrollScreenBinding;
import com.src.roadsafety.mirosScreenViewHolder;
import com.src.roadsafety.MirosList;


public class ScrollScreen extends AppCompatActivity {

    private static final String TAG = "Miros";
    public static final String ANONYMOUS = "anonymous";
    public static String MESSAGES_CHILD;
    private ScrollScreenBinding mBinding;
    private LinearLayoutManager mLinearLayoutManager;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mDatabase;
    final String[] userType = {ANONYMOUS};
    private FirebaseRecyclerAdapter<MirosList, mirosScreenViewHolder> mMirosContent;
    ImageButton miros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ScrollScreenBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


    }
    @Override
    public void onStart() {
        super.onStart();
        MESSAGES_CHILD = "Database/miros";

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setStackFromEnd(true);

        mBinding.messageRecyclerViewChoiceScreen.setLayoutManager(mLinearLayoutManager);

        // Initialize Realtime Database
        mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference messagesRef = mDatabase.getReference().child(MESSAGES_CHILD);

        messagesRef.get().addOnCompleteListener(task -> {
            if (task.getResult().getValue()==null) {
                mBinding.IntructorLoader.setVisibility(ProgressBar.INVISIBLE);
            }

        });

        FirebaseRecyclerOptions<MirosList> options =
                new FirebaseRecyclerOptions.Builder<MirosList>()
                        .setQuery(messagesRef, MirosList.class)
                        .build();

        mMirosContent = new FirebaseRecyclerAdapter<MirosList, mirosScreenViewHolder>(options) {
            @Override
            public mirosScreenViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new mirosScreenViewHolder(inflater.inflate(R.layout.miros_view, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(mirosScreenViewHolder vh, int position, MirosList message) {
                mBinding.IntructorLoader.setVisibility(ProgressBar.INVISIBLE);
                vh.bindMessage(message);

            }


        };

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setStackFromEnd(true);
        mBinding.messageRecyclerViewChoiceScreen.setLayoutManager(mLinearLayoutManager);
        mBinding.messageRecyclerViewChoiceScreen.setAdapter(mMirosContent);

        // Scroll down when a new message arrives
        // See MyScrollToBottomObserver.java for details
        mMirosContent.registerAdapterDataObserver(
                new MyScrollToBottomObserver(mBinding.messageRecyclerViewChoiceScreen, mMirosContent, mLinearLayoutManager));

        mMirosContent.startListening();

    }



}
