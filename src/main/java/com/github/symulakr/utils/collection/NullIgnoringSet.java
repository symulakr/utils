package com.github.symulakr.utils.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Sets;

public class NullIgnoringSet<E>
      extends ForwardingSet<E>
      implements Serializable
{

   private final Set<E> delegate = Sets.newHashSet();

   protected NullIgnoringSet()
   {
   }

   protected NullIgnoringSet(Iterable<E> iterable)
   {
      for (E element : iterable)
      {
         add(element);
      }
   }

   protected NullIgnoringSet(E... elements)
   {
      for (E element : elements)
      {
         add(element);
      }
   }

   @Override
   protected Set<E> delegate()
   {
      return delegate;
   }

   @Override
   public boolean add(E element)
   {
      if (element == null)
      {
         return false;
      }
      return super.add(element);
   }

   @Override
   public boolean addAll(Collection<? extends E> collection)
   {
      return super.addAll(IterableUtils.nonNullElements(collection));
   }

   public static <E> NullIgnoringSet<E> newInstance()
   {
      return new NullIgnoringSet<E>();
   }

   public static <E> NullIgnoringSet<E> newInstance(Iterable<E> iterable)
   {
      return new NullIgnoringSet<E>(iterable);
   }

   public static <E> NullIgnoringSet<E> newInstance(E... elements)
   {
      return new NullIgnoringSet<E>(elements);
   }
}
