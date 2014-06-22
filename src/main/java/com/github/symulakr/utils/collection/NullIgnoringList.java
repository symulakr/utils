package com.github.symulakr.utils.collection;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class NullIgnoringList<E>
      extends ForwardingList<E>
      implements List<E>, Serializable
{

   private final List<E> delegate = Lists.newArrayList();

   @Override
   protected List<E> delegate()
   {
      return delegate;
   }

   protected NullIgnoringList()
   {
   }

   protected NullIgnoringList(Iterable<E> iterable)
   {
      for (E element : iterable)
      {
         add(element);
      }
   }

   protected NullIgnoringList(E... elements)
   {
      for (E element : elements)
      {
         add(element);
      }
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

   @Override
   public boolean addAll(int index, Collection<? extends E> elements)
   {
      return super.addAll(index, IterableUtils.nonNullElements(elements));
   }

   public static <E> NullIgnoringList<E> newInstance()
   {
      return new NullIgnoringList<E>();
   }

   public static <E> NullIgnoringList<E> newInstance(Iterable<E> iterable)
   {
      return new NullIgnoringList<E>(iterable);
   }

   public static <E> NullIgnoringList<E> newInstance(E... elements)
   {
      return new NullIgnoringList<E>(elements);
   }

}
