package com.github.symulakr.utils.collection;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.Maps;

public class NullIgnoringMap<K, V>
      extends ForwardingMap<K, V>
      implements Serializable
{
   private final Map<K, V> delegate = Maps.newHashMap();

   protected NullIgnoringMap()
   {
   }

   protected NullIgnoringMap(Map<K, V> map)
   {
      putAll(map);
   }

   @Override
   protected Map<K, V> delegate()
   {
      return delegate;
   }

   @Override
   public V put(K key, V value)
   {
      if (containNull(key, value))
      {
         return null;
      }
      return super.put(key, value);
   }

   @Override
   public void putAll(Map<? extends K, ? extends V> map)
   {
      if (map == null || map.isEmpty())
      {
         return;
      }
      for (Entry<? extends K, ? extends V> entry : map.entrySet())
      {
         K key = entry.getKey();
         V value = entry.getValue();
         if (!containNull(key, value))
         {
            super.put(key, value);
         }
      }
   }

   private boolean containNull(K key, V value)
   {
      return IterableUtils.containsNull(key, value);
   }

   public static <K, V> NullIgnoringMap<K, V> newInstance()
   {
      return new NullIgnoringMap<K, V>();
   }

   public static <K, V> NullIgnoringMap<K, V> newInstance(Map<K, V> map)
   {
      return new NullIgnoringMap<K, V>(map);
   }

}
