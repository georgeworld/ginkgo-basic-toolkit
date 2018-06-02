package com.georgeinfo.basic.toolkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author George (GeorgeWorld@qq.com)
 */
public class MultiKeyMapKey {
    private Integer trueKey;
    private List<Object> keyList = new ArrayList<Object>();


    public Integer getTrueKey() {
        return trueKey;
    }

    public void setTrueKey(Integer trueKey) {
        this.trueKey = trueKey;
    }

    public void addKey(Object key) {
        keyList.add(key);
    }

    public List<Object> getKeyList() {
        return keyList;
    }

    public void bindingKeys(MultiKeyMapKey newKey) {
        List<Object> newKeyList = new ArrayList<Object>();
        for (Object key : keyList) {
            for (Object nkey : newKey.getKeyList()) {
                if (!(key == nkey || (key.getClass() == nkey.getClass()) && (key.equals(nkey)))) {
                    newKeyList.add(nkey);
                }
            }
        }

        if (!newKeyList.isEmpty()) {
            keyList.addAll(newKeyList);
        }
    }

    public boolean keyEquals(MultiKeyMapKey newKey) {
        if (this == newKey) {
            return true;
        }
        if (this.trueKey == newKey.getTrueKey()) {
            return true;
        }

        for (Object key : keyList) {
            for (Object nkey : newKey.getKeyList()) {
                if ((key.getClass() == nkey.getClass()) && key.equals(nkey)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiKeyMapKey that = (MultiKeyMapKey) o;
        return Objects.equals(trueKey, that.trueKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trueKey);
    }
}
