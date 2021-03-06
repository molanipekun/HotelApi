????   49
 E ? ?
 ? ?	  ?
  ?  ? ?
  ? ? ? ? z  ? ?
  ?
 
 ?  ?  ?
 ? ?	  ? ? ? ? ? ? ? ?
 ? ?	  ?      ?
  ?
 
 ?
 
 ?
 ? ? ? ? ? ? ? ? ? ? ? ?
  ?
  ? ?
 * ? ? ? ? ? ?
  ?
  ?
 ? ? ? ?
  ? ? ?	 ? ? ? ? ? ?
  ?
  ?
 ? ?
  ?
  ?  ? ? ?
  ?	  ? ? ?	  ? ? ?
  ? ? ? logger Lorg/slf4j/Logger; serialVersionUID J ConstantValue?.W5!??q CLAIM_KEY_USERNAME Ljava/lang/String; CLAIM_KEY_AUDIENCE CLAIM_KEY_CREATED AUDIENCE_UNKNOWN AUDIENCE_WEB AUDIENCE_MOBILE AUDIENCE_TABLET secret RuntimeVisibleAnnotations 4Lorg/springframework/beans/factory/annotation/Value; value ${jwt.secret} agentRepository 2Lregistration/login/repository/CustomerRepository; 8Lorg/springframework/beans/factory/annotation/Autowired; 
expiration Ljava/lang/Long; ${jwt.expiration} userDetailsService BLorg/springframework/security/core/userdetails/UserDetailsService; 8Lorg/springframework/beans/factory/annotation/Qualifier; 
jwtservice <init> ()V Code LineNumberTable LocalVariableTable this .Lregistration/login/security/jwt/JwtTokenUtil; getUsernameFromToken &(Ljava/lang/String;)Ljava/lang/String; claims Lio/jsonwebtoken/Claims; username e Ljava/lang/Exception; token StackMapTable ? ? MethodParameters getCreatedDateFromToken $(Ljava/lang/String;)Ljava/util/Date; created Ljava/util/Date; ? getExpirationDateFromToken getAudienceFromToken audience getClaimsFromToken ,(Ljava/lang/String;)Lio/jsonwebtoken/Claims; ? generateExpirationDate ()Ljava/util/Date; isTokenExpired '(Ljava/lang/String;)Ljava/lang/Boolean;  isCreatedBeforeLastPasswordReset 5(Ljava/util/Date;Ljava/util/Date;)Ljava/lang/Boolean; lastPasswordReset generateAudience >(Lorg/springframework/mobile/device/Device;)Ljava/lang/String; device *Lorg/springframework/mobile/device/Device; ignoreTokenExpiration generateToken y(Lorg/springframework/security/core/userdetails/UserDetails;Lorg/springframework/mobile/device/Device;)Ljava/lang/String; userDetails ;Lorg/springframework/security/core/userdetails/UserDetails; Ljava/util/Map; LocalVariableTypeTable 5Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>; O(Lorg/springframework/security/core/userdetails/UserDetails;)Ljava/lang/String; #(Ljava/util/Map;)Ljava/lang/String; 	Signature I(Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;)Ljava/lang/String; canTokenBeRefreshed 7(Ljava/lang/String;Ljava/util/Date;)Ljava/lang/Boolean; refreshToken refreshedToken validateToken 9(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Boolean; clientId agent #Lregistration/login/model/Customer; ? getToken agentId 
SourceFile JwtTokenUtil.java *Lorg/springframework/stereotype/Component; e f ,registration/login/security/jwt/JwtTokenUtil ? ? ? G H ? ? ? ? java/lang/Exception ? ? ? ? ? java/util/Date  java/lang/Long e ? java/lang/String V O	
 io/jsonwebtoken/Claims ^ _ } y unknown web tablet mobile ~ m java/util/HashMap sub  ?!"# ? ? ? ?$%&'( ? ?)*+,-./0 ? x y ? ?1 ? ? ? ? ?2 l m [ \345 a b678 ? ? java/lang/Object java/io/Serializable !registration/login/model/Customer org/slf4j/LoggerFactory 	getLogger %(Ljava/lang/Class;)Lorg/slf4j/Logger; 
getSubject ()Ljava/lang/String; 
getMessage org/slf4j/Logger error (Ljava/lang/String;)V get &(Ljava/lang/Object;)Ljava/lang/Object; 	longValue ()J (J)V getExpiration io/jsonwebtoken/Jwts parser ()Lio/jsonwebtoken/JwtParser; io/jsonwebtoken/JwtParser setSigningKey /(Ljava/lang/String;)Lio/jsonwebtoken/JwtParser; parseClaimsJws )(Ljava/lang/String;)Lio/jsonwebtoken/Jws; io/jsonwebtoken/Jws getBody ()Ljava/lang/Object; java/lang/System currentTimeMillis before (Ljava/util/Date;)Z java/lang/Boolean valueOf (Z)Ljava/lang/Boolean; (org/springframework/mobile/device/Device isNormal ()Z isTablet isMobile equals (Ljava/lang/Object;)Z 9org/springframework/security/core/userdetails/UserDetails getUsername java/util/Map put 8(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; builder ()Lio/jsonwebtoken/JwtBuilder; io/jsonwebtoken/JwtBuilder 	setClaims -(Ljava/util/Map;)Lio/jsonwebtoken/JwtBuilder; setExpiration .(Ljava/util/Date;)Lio/jsonwebtoken/JwtBuilder; "io/jsonwebtoken/SignatureAlgorithm HS512 $Lio/jsonwebtoken/SignatureAlgorithm; signWith T(Lio/jsonwebtoken/SignatureAlgorithm;Ljava/lang/String;)Lio/jsonwebtoken/JwtBuilder; compact booleanValue *(Ljava/lang/String;Ljava/lang/Throwable;)V 0registration/login/repository/CustomerRepository findByUsername 7(Ljava/lang/String;)Lregistration/login/model/Customer; @org/springframework/security/core/userdetails/UserDetailsService loadUserByUsername O(Ljava/lang/String;)Lorg/springframework/security/core/userdetails/UserDetails; !  E  F   G H    I J  K    L  N O  K    ,  P O  K      Q O  K      R O  K    !  S O  K    #  T O  K    '  U O  K    %  V O  W     X  Ys Z  [ \  W     ]    ^ _  W     X  Ys `   a b  W     c  Ys d ]     e f  g   <     *? *? ? ?    h   
       i        j k    l m  g   ?     "*+? N-?  M? N*? -? ? 	 M,?        h       8  9  =  :  ;  <   > i   >    n o    p O    q r    " j k     " s O     p O  t    P u?  v w    s    x y  g   ?     1*+? N? 
Y-?  ? ? ? M? N*? -? ? 	 M,?        h       D  E  I  F   G - H / J i   >    n o    z {     q r    1 j k     1 s O  /  z {  t    _ u?  | w    s    } y  g   ?     "*+? N-?  M? N*? -? ? 	 M,?        h       P  Q  U  R  S  T   V i   >    n o    ^ {    q r    " j k     " s O     ^ {  t    P u?  | w    s    ~ m  g   ?     '*+? N-?  ? M? N*? -? ? 	 M,?        h       \  ]  b  ^  _ # a % c i   >    n o     O    q r    ' j k     ' s O  %   O  t    U u?  v w    s    ? ?  g   ?     0? *? ?  +?  ?  ? M? N*? -? ? 	 M,?        h   & 	   i  j  k  l  p  m  n , o . q i   4    n o    q r    0 j k     0 s O  .  n o  t    ^ u?  ? w    s    ? ?  g   A     ? 
Y? *? ?  ia? ?    h       u i        j k    ? ?  g   W     *+? M,? 
Y? ? ?  ?    h   
    y  z i         j k      s O    ^ {  w    s    ? ?  g   ^     ,? +,? ? ? ?  ?    h       ~ i         j k      z {     ? {  t    @ w   	 z   ?    ? ?  g   ?     3!M+? -+? " ? 	#M? +? $ ? 	%M? +? & ? 'M,?    h   & 	   ?  ?  ?  ?  ?  ? % ? . ? 1 ? i        3 j k     3 ? ?   0  O  t   
 ?  v w    ?    ? ?  g   t     !*+? (M%,? )? ',? )? ? ?  ?    h   
    ?  ? i        ! j k     ! s O     O  t    ?  v@ w    s    ? ?  g   ?     ;? *Y? +N-,+? - ? . W-*,? /? . W-? 
Y? ? . W*-? 0?    h       ?  ?  ? % ? 5 ? i   *    ; j k     ; ? ?    ; ? ?   3 n ?  ?      3 n ?  w   	 ?   ?    ? ?  g   ?     -? *Y? +M,,+? - ? . W,? 
Y? ? . W*,? 0?    h       ?  ?  ? ' ? i        - j k     - ? ?   % n ?  ?      % n ?  w    ?    ? m  g   ?     (? *Y? +M,,+? . W,? 
Y? ? . W*,? 0?    h       ?  ?  ? " ? i        ( j k     ( p O     n ?  ?        n ?  w    p     ? ?  g   ~     $? 1+? 2 *? 3? 4 ? 5*? ? 6 ? 7 ?    h       ?  ? 
 ?  ?  ? # ? i       $ j k     $ n ?  ?       $ n ?  w    n   ?    ?  ? ?  g   ?     1*+? 8N*-,? 9? :? *+? ;? :? *+? <? :? ? ?  ?    h       ?  ?  ? - ? i   *    1 j k     1 s O    1 ? {   + z {  t    ? ( |@ w   	 s   ?    ? m  g   ?     2*+? N-? 
Y? ? = W*-? 0M? N*? -? -? > M,?        h   "    ?  ?  ?  ?  ?   ? . ? 0 ? i   >    n o    ? O     q r    2 j k     2 s O  0  ? O  t    _ u?  v w    s    ? ?  g   ?     8*+? ?N*? @-? A :? ?  ?-,? )? *+? ;? :? ? ?  ?    h       ?  ?  ?  ?  ? i   4    8 j k     8 s O    8 ? O   2 p O   & ? ?  t    ?  v ?@ w   	 s   ?    ? m  g   d     *? B+? C M*,? DN-?    h       ?  ?  ? i   *     j k      ? O   	 ? ?    s O  w    ?    ?    ? W     ?  