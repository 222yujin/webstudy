package kr.or.ddit.utils;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;

/**
 * Marshalling : 특정 언어로 표현된 데이터(java object)를 범용 표현 방식(json/xml)으로 변환하는 작업
 *
 */
public class MarshallingUtils {
   public String marshalling(Object target) {
      // int(Integer),double(Double),char,String,StringBuffer

      if (ClassUtils.isPrimitiveOrWrapper(target.getClass())
            || CharSequence.class.isAssignableFrom(target.getClass())) {
         throw new IllegalArgumentException("마샬링이 불가능한 데이터");
      }
      return marshallingObjectToJson(target);

   }

   private String marshallingObjectToJson(Object target) {
      if (target == null) {
         return null;
      }
      Class<?> targetType = target.getClass();
      String value = null;
      // String 타입을 잡음
      if (CharSequence.class.isAssignableFrom(targetType) ||
      // char타입을 잡음
            ClassUtils.isAssignable(targetType, Character.class, true)) {
         value = String.format("\"%s\"", target);
      } else if (ClassUtils.isPrimitiveOrWrapper(targetType)) {
         value = target.toString();
      } else if (targetType.isArray()) {
         // Object[] array = (Object[]) target;
         value = marshallingArrayToJson(target);

      } else if (target instanceof List) {
      value = marshallingListToJson(target);
     }else if (target instanceof Map) {
         Map map = (Map) target;
         value = marshallingMapToJson(map);
      } else {
         StringBuffer json = new StringBuffer("{");
         Field[] fields = targetType.getDeclaredFields();
         for (Field tmp : fields) {
            String name = tmp.getName();
            try {
               PropertyDescriptor pd = new PropertyDescriptor(name, targetType);
               Object propValue = pd.getReadMethod().invoke(target);
               json.append(String.format(PROPPATTERN, name, marshallingObjectToJson(propValue)));
            } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException
                  | InvocationTargetException e) {
               // getter가 없는 경우 다음 요소로 넘어가기
               System.err.println(e.getMessage());
               continue;
            }

         }
         int lastIndex = json.lastIndexOf(",");
         if (lastIndex == json.length() - 1) {
            json.deleteCharAt(lastIndex);
         }
         json.append("}");
         value = json.toString();
      }
      return value;
   }

   

private final String PROPPATTERN = "\"%s\":%s,";

   private String marshallingMapToJson(Map map) {
      // 순서가 있는 것 처럼 접근 가능
      Iterator<?> keys = map.keySet().iterator();
      StringBuffer json = new StringBuffer("{");
      while (keys.hasNext()) {
         Object key = (Object) keys.next();
         Object value = map.get(key);
         json.append(String.format(PROPPATTERN, key, marshallingObjectToJson(value)));

      }
      int lastIndex = json.lastIndexOf(",");
      if (lastIndex == json.length() - 1) {
         json.deleteCharAt(lastIndex);
      }
      json.append("}");
      return json.toString();
   }

   private String marshallingListToJson(Object target) {
      StringBuffer json = new StringBuffer("[");
         if (target != null) {
            List list = (List)target;
         int length = list.size();
            // 배열에있는 요소 꺼내기
            for (int i=0; i<length; i++) {
               json.append(marshallingObjectToJson(list.get(i)) + ",");
            }
            int lastIndex = json.lastIndexOf(",");
            if (lastIndex == json.length() - 1) {
               json.deleteCharAt(lastIndex);
            }
         }
         json.append("]");
         return json.toString();
   }
   
   public String marshallingArrayToJson(Object array) {
      StringBuffer json = new StringBuffer("[");
      if (array != null) {
      int length = Array.getLength(array);
         // 배열에있는 요소 꺼내기
         for (int i=0; i<length; i++) {
            json.append(marshallingObjectToJson(Array.get(array, i)) + ",");
         }
         int lastIndex = json.lastIndexOf(",");
         if (lastIndex == json.length() - 1) {
            json.deleteCharAt(lastIndex);
         }
      }
      json.append("]");
      return json.toString();
   }

   // 검증용
 
}