//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `TaskList.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package TaskList;

public class Task implements java.lang.Cloneable,
                             java.io.Serializable
{
    public String name;

    public String description;

    public Task()
    {
        this.name = "";
        this.description = "";
    }

    public Task(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        Task r = null;
        if(rhs instanceof Task)
        {
            r = (Task)rhs;
        }

        if(r != null)
        {
            if(this.name != r.name)
            {
                if(this.name == null || r.name == null || !this.name.equals(r.name))
                {
                    return false;
                }
            }
            if(this.description != r.description)
            {
                if(this.description == null || r.description == null || !this.description.equals(r.description))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::TaskList::Task");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, name);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, description);
        return h_;
    }

    public Task clone()
    {
        Task c = null;
        try
        {
            c = (Task)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeString(this.name);
        ostr.writeString(this.description);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.name = istr.readString();
        this.description = istr.readString();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, Task v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public Task ice_read(com.zeroc.Ice.InputStream istr)
    {
        Task v = new Task();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<Task> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, Task v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<Task> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(Task.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final Task _nullMarshalValue = new Task();

    /** @hidden */
    public static final long serialVersionUID = 2063509832L;
}
