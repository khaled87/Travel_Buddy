package travelbuddy.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicEANRequest {
    private final String cid = "55505";// For EAN test API
    private final String apiKey = "r9v7gm2dr4efxdss9824ta57";
    private String locale = "en_US";
    private String currencyCode = "USD";
    private String customerSessionId;
    private String cacheKey;
    private String cacheLocation;

    public String getCid() {
        return cid;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCustomerSessionId() {
        return customerSessionId;
    }

    public void setCustomerSessionId(String customerSessionId) {
        this.customerSessionId = customerSessionId;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String getCacheLocation() {
        return cacheLocation;
    }

    public void setCacheLocation(String cacheLocation) {
        this.cacheLocation = cacheLocation;
    }
    
    /**
    * Create a REST parameter string conforming with EAN API based on the current data.
    * @return the EAN REST parameter string.
    */
    public String toRESTParamString() {
        List<Field> allFields = new ArrayList<>();
        getAllFields(allFields, this.getClass());

        String result = "";
        for (Field field : allFields) {
            try {
                String fieldValue = getFieldValue(field, this);
                if (fieldValue != null && !"".equals(fieldValue)) {
                    result += "&" + field.getName() + "=" + fieldValue;
                }
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
                Logger.getLogger(BasicEANRequest.class.getName()).log(Level.WARNING, null, ex);
            }
        }
        return result;
    }
    
    private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    private static String getFieldValue(Field field, Object object) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = object.getClass().getMethod("get" + capitalizeFirstLetter(field.getName()));
        Object returnValue = method.invoke(object);
        if (returnValue != null && returnValue.getClass().equals(Date.class)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(returnValue);
        }
        return returnValue == null ? null : returnValue.toString();
    }

    private static String capitalizeFirstLetter(String original) {
        if (original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }
}
