package com.github.symulakr.utils.collection;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class IterableUtils
{

   public static <T extends Iterable<?>> boolean isEmpty(T iterable)
   {
      return !isNotEmpty(iterable);
   }

   public static <T extends Collection<?>> boolean isEmpty(T collection)
   {
      return collection == null || collection.isEmpty();
   }

   public static <T extends Collection<?>> boolean isNotEmpty(T collection)
   {
      return !isEmpty(collection);
   }

   public static <T extends Iterable<?>> boolean isNotEmpty(T iterable)
   {
      return iterable != null && iterable.iterator()
            .hasNext();
   }

   public static <E> List<? extends E> nonNullElements(Collection<? extends E> collection)
   {
      if (IterableUtils.isEmpty(collection))
      {
         return Collections.emptyList();
      }
      List<E> nonNull = Lists.newArrayList();
      for (E element : collection)
      {
         if (element != null)
         {
            nonNull.add(element);
         }
      }
      return nonNull;
   }

   public static <T extends Iterable<?>> boolean containsNull(T iterable)
   {
      for (Object element : iterable)
      {
         if (element == null)
         {
            return true;
         }
      }
      return false;
   }

   public static <T> boolean containsNull(T... elements)
   {
      return containsNull(Lists.newArrayList(elements));
   }
}
