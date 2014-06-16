package com.github.symulakr.utils.collection;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CountedSet<E>
      extends AbstractSet<E>
      implements Set<E>
{

   private HashMap<E, int[]> map;

   public CountedSet()
   {
      map = new HashMap<E, int[]>();
   }

   public CountedSet(int initialCapacity)
   {
      map = new HashMap<E, int[]>(initialCapacity);
   }

   public CountedSet(Collection<? extends E> c)
   {
      map = new HashMap<E, int[]>(Math.max((int) (c.size() / .75f) + 1, 16));
      addAll(c);
   }

   @Override
   public int size()
   {
      return map.size();
   }

   @Override
   public boolean isEmpty()
   {
      return map.isEmpty();
   }

   @Override
   public boolean contains(Object o)
   {
      return map.containsKey(o);
   }

   @Override
   public Iterator<E> iterator()
   {
      return map.keySet().iterator();
   }

   public boolean subtract(E e)
   {
      int[] value = map.get(e);
      return value == null || --value[0] == 0 && remove(e);
   }

   public int getCount(E e)
   {
      int[] value = map.get(e);
      return value != null ? value[0] : 0;
   }

   public void setCount(E e, int count)
   {
      if (count > 0)
      {
         int[] value = map.get(e);
         if (value != null)
         {
            value[0] = count;
         }
         else
         {
            map.put(e, new int[] { count });
         }
      }
      else
      {
         remove(e);
      }
   }

   public Set<Map.Entry<E, Integer>> entrySet()
   {
      Map<E, Integer> result = new HashMap<E, Integer>(map.size());
      for (Map.Entry<E, int[]> entry : map.entrySet())
      {
         result.put(entry.getKey(), entry.getValue()[0]);
      }
      return result.entrySet();
   }

   @Override
   public boolean add(E e)
   {
      int[] value = map.get(e);
      if (value != null)
      {
         value[0]++;
         return false;
      }
      return map.put(e, new int[] { 1 }) == null;
   }

   @Override
   public boolean remove(Object o)
   {
      return map.remove(o) != null;
   }

   @Override
   public void clear()
   {
      map.clear();
   }

}
