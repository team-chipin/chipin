package cz.cvut.fit.sp.chipin.application.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cz.cvut.fit.sp.chipin.application.R;

public class GroupsItem extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.item_groups, container, false);

        RecyclerView view = root.findViewById(R.id.groupsRecyclerView);

        GroupsAdapter adapter = new GroupsAdapter(40);

        view.setLayoutManager(new LinearLayoutManager(getActivity()));

        view.setAdapter(adapter);

        return root;

    }

}
