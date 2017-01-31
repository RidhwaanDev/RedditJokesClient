package com.example.ridhwaan.redditforfaez;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;



import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RedditListFragment extends Fragment implements AsyncResponse




{

    private RecyclerView mRecyclerView;
    private RedditAdapter mAdapter;

    public  List<RedditObject> mObjectList;
    private int index;
    private boolean isClicked = false;

    private SwipeRefreshLayout mReloadView;
    private TextView title;

    private static final String DIALOG_ID = "id";
    private static final int REQUEST_CODE_TO_CREATE_DIALOG = 1;


    private static List<RedditObject> sObjectList;
    Parser parse;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_reddit_list, container, false);
              mRecyclerView = (RecyclerView) view.findViewById(R.id.reddit_list_view);
              mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
              mReloadView = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);


                mReloadView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        updateUI();
                    }
                });

        executeAsyncTask();



        Log.d("OBJECT", "Object was this time for real" + mObjectList);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_crime_list,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_item_new_post){

            createDialog();

            return true;


        }

  return false;


    }

    public void createDialog(){
        FragmentManager fg = getFragmentManager();
        CreateObjectFragment dialogFragment = new CreateObjectFragment();
        dialogFragment.show(fg,DIALOG_ID);
        dialogFragment.setTargetFragment(RedditListFragment.this, REQUEST_CODE_TO_CREATE_DIALOG );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK ){
            return;
        }

        if(requestCode == REQUEST_CODE_TO_CREATE_DIALOG){

            RedditObject obj = (RedditObject) data.getSerializableExtra(CreateObjectFragment.ARG_REDDIT_OBJ);
            RedditObjectStash.get(getActivity()).addRedditObject(obj);
            updateUI();
        }
    }

    public void executeAsyncTask(){
        DownLoadRawData downLoadRawData = new DownLoadRawData();
        downLoadRawData.delegate = this;
        downLoadRawData.execute("https://www.reddit.com/r/Jokes/.json");

        // Log.d("ERR", " TEXT WAS "  + jsonTEXT);
    }
     public void processFinish(String output) {

        Log.d("TAG", "process finished reached");
        parse = new Parser(output);

        this.mObjectList = parse.getRedditObjectsList();


        RedditObjectStash sRStash = RedditObjectStash.get(getActivity());
        sRStash.setList(mObjectList);

        Log.d("TAG", "OBJECT LIST WAS" + mObjectList.toString());
        Log.d("TAG", "The List to STring is " + parse.getRedditObjectsList().toString());

        updateUI();

    }
  //  @Override
    /*public void onResume() {
        super.onResume();
        updateUI();
    } // fix  this later*/

    public void updateUI(){
        Log.d("UPDATE", "HERE IT GOES BOYS" + sObjectList);





       // mAdapter = new RedditAdapter(listOfRedditObject);

        if(mAdapter == null) {
            mAdapter = new RedditAdapter(mObjectList);
            mRecyclerView.setAdapter(mAdapter);

        }

        else {


            mAdapter.notifyDataSetChanged();
            mReloadView.setRefreshing(false);
            Toast.makeText(getActivity(),"Refreshed!", Toast.LENGTH_SHORT);

        }

         // mAdapter.notifyDataSetChanged();

    }

    private class RedditHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView upvote;
        private TextView titleOfPost;
        private TextView author;
        private RedditObject redditObject;

        private int index;

        public RedditHolder(View itemView) {
            super(itemView);

            titleOfPost = (TextView) itemView.findViewById(R.id.title_reddit_view);//by casting itemView to a TextView, this forces client to pass text view
            author = (TextView) itemView.findViewById(R.id.author_reddit_view);
            upvote = (TextView) itemView.findViewById(R.id.upvote_text_view);



            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if(!redditObject.isNSFW())  {  titleOfPost.setTextColor(Color.BLUE);}

            isClicked  = true;

            Toast.makeText(getActivity(), "Index is " + index, Toast.LENGTH_SHORT).show();
            //mRecyclerView.getAdapter().notifyItemMoved(0,2);
           // Intent i = DetailActivity.startIntent(getActivity(),mObjectList.get(index).getPostID());
             Intent i = RedditPagerActivity.newInstance(getActivity(),mObjectList.get(index).getPostID());
            startActivity(i);


        }

        public void bindHolder(RedditObject redditObj, int pos) {
            redditObject = redditObj;
            index = pos;
            titleOfPost.setText(redditObject.getmTitle());


            if(redditObject.isNSFW()){
                titleOfPost.setTextColor(Color.RED);
            }

            author.setText( "-" + redditObject.getmAuthor());
            upvote.setText(Integer.toString(redditObject.getmUpvotes()));


        }



    }


    private class RedditAdapter extends RecyclerView.Adapter<RedditHolder>{


        private List<RedditObject> mListOfRedditObjects;


        public RedditAdapter(List<RedditObject> listOfRedditObject){
           mListOfRedditObjects = listOfRedditObject;

        }

        @Override
        public RedditHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.single_reddit_listing, parent, false);
            return new RedditHolder(view);
        }

        @Override
        public void onBindViewHolder(RedditHolder holder, int position) {
                RedditObject redditObject = mListOfRedditObjects.get(position);

             //   holder.titleOfPost.setText(redditObject.getmTitle());
             //   older.author.setText( "-" + redditObject.getmAuthor());
             //   holder.upvote.setText(Integer.toString(redditObject.getmUpvotes()));
                 holder.bindHolder(redditObject, position);

        }

        @Override
        public int getItemCount() {
         //   Log.d("TAG ", "In the adapter and can confirm" + mListOfRedditObjects.size());

            return mListOfRedditObjects.size();
        }
    }

}
