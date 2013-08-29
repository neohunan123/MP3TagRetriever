package org.farng.mp3.object;

import java.io.UnsupportedEncodingException;

/**
 * ID3v2 and Lyrics3v2 tags have individual fields <code>AbstractMP3Fragment</code>s Then each fragment is broken down
 * in to individual <code>AbstractMP3Object</code>s
 *
 * @author Eric Farng
 * @version $Revision: 1.5 $
 */
public class ObjectStringSizeTerminated extends AbstractMP3Object {

    /**
     * Creates a new ObjectStringSizeTerminated object.
     */
    public ObjectStringSizeTerminated(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Creates a new ObjectStringSizeTerminated object.
     */
    public ObjectStringSizeTerminated(final ObjectStringSizeTerminated object) {
        super(object);
    }

    public int getSize() {
        final String str = writeString();
        if (str != null) {
            return str.length();
        }
        return 0;
    }

    public boolean equals(final Object obj) {
        if (obj instanceof ObjectStringSizeTerminated == false) {
            return false;
        }
        return super.equals(obj);
    }

    public void readString(final String str, final int offset) {
    	if (str == null) {
            throw new NullPointerException("String is null");
        }
        if ((offset < 0) || (offset >= str.length())) {
            throw new IndexOutOfBoundsException("Offset to String is out of bounds: offset = " +
                                                offset +
                                                ", string.length()" +
                                                str.length());
        }
    	this.value = str.substring(offset);
    }
    public void readString(final byte[] arr, final int offset, String identifier) {
        if (arr == null) {
            throw new NullPointerException("String is null");
        }
        if ((offset < 0) || (offset >= arr.length)) {
            throw new IndexOutOfBoundsException("Offset to String is out of bounds: offset = " +
                                                offset +
                                                ", string.length()" +
                                                arr.length);
        }
    	
        if(identifier.equals("UTF-16")) {
        	byte[] brr =new byte[arr.length-2-offset];
    		for(int i =0;i<arr.length-2-offset;i++) {
    			brr[i] = arr[i+2+offset];
    		}
    		try {
				String n = new String(brr,"UTF-16LE");
				this.value = n;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if(identifier.equals("UTF-16BE")) {
        	byte[] brr =new byte[arr.length-2-offset];
    		for(int i =0;i<arr.length-2-offset;i++) {
    			brr[i] = arr[i+2+offset];
    		}
    		try {
				String n = new String(brr,"UTF-16BE");
				this.value = n;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if(identifier.equals("UTF-8")) {
        	byte[] brr =new byte[arr.length-offset];
    		for(int i =0;i<arr.length-offset;i++) {
    			brr[i] = arr[i+offset];
    		}
    		try {
				String n = new String(brr,"UTF-8");
				this.value = n;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else{
        	byte[] brr =new byte[arr.length-offset];
    		for(int i =0;i<arr.length-offset;i++) {
    			brr[i] = arr[i+offset];
    		}
    		try {
				String n = new String(brr,"GBK");
				this.value = n;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	
        //this.value = str.substring(offset);
    }

    public String toString() {
        return writeString();
    }

    public String writeString() {
        return (String) this.value;
    }
}