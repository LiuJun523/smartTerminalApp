package com.iss.smartterminal.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil{

    /**
     * 获取一个非null的字符串
     *
     * @param str
     * @return
     */
    public static String getString( String str ){
        return str == null ? "" : str;
    }

    /**
     * 判断字符是否为空
     *
     * @param value
     * @return
     */
    public static Boolean isEmpty( String value ){
        return value == null || value.length() == 0;
    }
    
    /**
     * 判断字符是否为数字
     * @param str
     * @return
     */
    public static Boolean isNumeric(String str){ 
    	   Pattern pattern = Pattern.compile("[0-9]+"); 
    	   return pattern.matcher(str).matches();
    	}

    /**
     * 字符串转整形
     *
     * @param s
     * @param value
     * @return
     */
    public static int tryParseInt( String s, Integer value ){

        if( isEmpty( s ) ){
            return value;
        }
        try{
            return Integer.parseInt( s );
        }
        catch( Exception ex ){
            return value;
        }
    }

    /**
     * 字符串转long型
     *
     * @param s
     * @param value
     * @return
     */
    public static long tryParseLong( String s, Long value ){
        if( isEmpty( s ).booleanValue() ) return value.longValue();
        try{
            return Long.parseLong( s );
        }
        catch( Exception ex ){
        }
        return value.longValue();
    }

    /**
     * 字符串转浮点型
     *
     * @param s
     * @param defaultvalue(默认值)
     * @return
     */
    public static float tryParseFloat( String s, float defaultvalue ){
        if( isEmpty( s ) ) return defaultvalue;
        try{
            return Float.parseFloat( s );
        }
        catch( Exception ex ){
            return defaultvalue;
        }
    }

    /**
     * 字符串转double
     *
     * @param s
     * @param value
     * @return
     */
    public static double tryParseDouble( String s, Double value ){
        if( isEmpty( s ).booleanValue() ) return value.doubleValue();
        try{
            return Double.parseDouble( s );
        }
        catch( Exception ex ){
        }
        return value.doubleValue();
    }

    /**
     * 字符串截取
     *
     * @param strIn
     * @param len
     * @return
     * @throws Throwable
     */
    static public String StringLeft( String strIn, int len ) throws Throwable{
        if( strIn == null || strIn.length() == 0 ) return "";
        if( len > strIn.length() ) return strIn;
        return strIn.substring( 0, len );
    }

    /**
     * 字符串格式化，以{index}为占位符(类似.NET的format)
     *
     * @param format
     * {0}abc{1}
     * @param arr
     * @return
     */
    static Pattern formatPattern = Pattern.compile( "\\{(\\d)\\}" );

    public static String stringFormat( String format, Object... arr ){
        if( isEmpty( format ) ) return format;
        Matcher m = formatPattern.matcher( format );
        while( m.find() ){
            Object f = arr[ Integer.parseInt( m.group( 1 ) ) ];
            format = format.replace( m.group(), f == null ? "" : f.toString() );
        }
        return format;
    }

    /**
     * 替换字符串（非正则替换）
     *
     * @param strSource
     * @param strFrom   （不能是正则表达式）
     * @param strTo
     * @return
     */
    public static String replaceAll( String strSource, String strFrom, String strTo ){
        if( strSource == null || strFrom == null || strTo == null ){
            return strSource;
        }
        int i = 0;
        if( ( i = strSource.indexOf( strFrom, i ) ) >= 0 ){
            char[] cSrc = strSource.toCharArray();
            char[] cTo = strTo.toCharArray();
            int len = strFrom.length();
            StringBuffer buf = new StringBuffer( cSrc.length );
            buf.append( cSrc, 0, i ).append( cTo );
            i += len;
            int j = i;
            while( ( i = strSource.indexOf( strFrom, i ) ) > 0 ){
                buf.append( cSrc, j, i - j ).append( cTo );
                i += len;
                j = i;
            }
            buf.append( cSrc, j, cSrc.length - j );
            return buf.toString();
        }
        return strSource;
    }

    /**
     * 格式化字符串
     *
     * @param strSource
     * @param strFrom
     * @return
     */
    public static String format( String strSource, String strFrom ){

        if( strSource == null || strFrom == null ){
            return strSource;
        }
        int i = 0;
        int j = 0;
        StringBuffer buffer = new StringBuffer();
        if( ( i = strSource.indexOf( "%s" ) ) >= 0 ){
            char[] cSrc = strSource.toCharArray();
            for( j = 0; j < i; j++ ){
                buffer.append( cSrc[ j ] );
            }

            buffer.append( strFrom );

            for( j = i + 2; j < strSource.length(); j++ ){
                buffer.append( cSrc[ j ] );
            }
            return buffer.toString();
        }
        return strSource;
    }

    /**
     * 字符串分割
     *
     * @param source
     * @param splitchar
     * @return
     */
    public static String[] split( String source, String splitchar ){
        if( isEmpty( source ) ) return null;
        String[] newArray = new String[ source.length() ];
        String temp = null;
        String[] result = null;
        int start = 0, end = 0, index = 0;
        for( int i = 0; i < source.length(); i++ ){
            temp = source.substring( i, i + 1 );
            if( splitchar.equals( temp ) ){
                temp = null;
                end = i;
                newArray[ index ] = source.substring( start, end );
                index++;
                start = end + 1;
            }
        }
        if( source.lastIndexOf( splitchar ) != source.length() ){
            newArray[ index ] = source.substring( start, source.length() );
            index++;
        }
        result = new String[ index ];
        System.arraycopy( newArray, 0, result, 0, index );
        return result;
    }

    /**
     * 字符串分割（去掉重复字符）
     *
     * @param source
     * @param splitchar
     * @param deemphasis
     * @return
     */
    public static String[] split( String source, String splitchar, boolean deemphasis ){
        if( isEmpty( source ) ) return null;
        String[] newArray = new String[ source.length() ];
        Map< String, String > removeArray = new HashMap< String, String >();
        String temp = null;
        String[] result = null;
        int start = 0, end = 0, index = 0;
        for( int i = 0; i < source.length(); i++ ){
            temp = source.substring( i, i + 1 );
            if( splitchar.equals( temp ) ){
                temp = null;
                end = i;
                String v = source.substring( start, end );
                if( !removeArray.containsKey( v ) ){
                    newArray[ index ] = v;
                    index++;
                    removeArray.put( v, "" );
                }
                start = end + 1;
            }
        }
        if( source.lastIndexOf( splitchar ) != source.length() ){
            String v = source.substring( start, source.length() );
            if( !removeArray.containsKey( v ) ){
                newArray[ index ] = v;
                index++;
            }
        }
        result = new String[ index ];
        System.arraycopy( newArray, 0, result, 0, index );
        return result;
    }

    /**
     * 字符串分割（去掉重复字符，按字符串长度排序）
     *
     * @param source
     * @param splitchar
     * @param deemphasis
     * @param sort
     * @return
     */
    public static String[] split( String source, String splitchar, boolean deemphasis, boolean sort ){
        String[] result = null;
        if( deemphasis ) result = split( source, splitchar, true );
        else result = split( source, splitchar );
        if( result == null ){
            return null;
        }
        if( sort ){
            Arrays.sort( result, new Comparator<Object>(){
                public int compare( Object a, Object b ){
                    String sa = ( String )a;
                    String sb = ( String )b;
                    return sb.length() - sa.length();
                }
            } );
        }
        return result;
    }

    /**
     * 去重并根据字符串长度排序
     *
     * @param source
     * @param splitchar
     * @return
     */
    public static String deemphasisAndsort( String source, String splitchar ){
        String[] result = split( source, splitchar, true, true );
        if( result == null ) return source;
        StringBuilder b = new StringBuilder();
        for( String r : result ){
            b.append( r ).append( splitchar );
        }
        return b.substring( 0, b.length() - 1 );
    }

	/**
	 * 将容易引起xss漏洞的半角字符直接替换成全角字符
	 * 
	 * @param s
	 * @return
	 */
	public static String xssEncode( String s ){

		if( s == null || s.isEmpty() ){
			return s;
		}
		StringBuilder sb = new StringBuilder( s.length() + 16 );
		for( int i = 0; i < s.length(); i++ ){
			char c = s.charAt( i );
			switch( c ){
				case '>' :
					sb.append( '＞' );// 全角大于号
					break;
				case '<' :
					sb.append( '＜' );// 全角小于号
					break;
				case '\'' :
					sb.append( '‘' );// 全角单引号
					break;
				case '\"' :
					sb.append( '“' );// 全角双引号
					break;
				case '&' :
					sb.append( '＆' );// 全角
					break;
				case '\\' :
					sb.append( '＼' );// 全角斜线
					break;
				case '#' :
					sb.append( '＃' );// 全角井号
					break;
				default :
					sb.append( c );
					break;
			}
		}
		return sb.toString();
	}
	
	public static boolean isSqlInject( String s ){

		String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|" + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
		Pattern sqlPattern = Pattern.compile( reg, Pattern.CASE_INSENSITIVE );
		
		if( sqlPattern.matcher( s ).find() ){
			return true;
		}
		return false;
	}
}
