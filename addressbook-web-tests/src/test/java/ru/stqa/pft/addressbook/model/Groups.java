package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 19.06.2016.
 */
public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData> delegate;

    public Groups(Groups groups) {
       this.delegate = new HashSet<GroupData>(groups.delegate());
    }

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }

    public Groups withAdded(GroupData group)
    {
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups without(GroupData group)
    {
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }

    public Groups withModified(GroupData group)
    {
        Groups groups = new Groups(this);
        GroupData modifiedGroup =  this.stream().filter(groupData -> groupData.getId()==group.getId()).iterator().next();
        groups.remove(modifiedGroup);
        groups.add(group);
        return groups;
    }
}
