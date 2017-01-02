CREATE DEFINER=`root`@`localhost` PROCEDURE `RTS_COMPUTRESULT`(IN `PARSE` bigint)
BEGIN
	DECLARE V_FIRST decimal(1) ;
	DECLARE V_SECOND decimal(1) ;
	DECLARE V_THIRD decimal(1) ;
	DECLARE V_FOURTH decimal(1);
	DECLARE V_FIFTH decimal(1) ;

	DECLARE V_FIRST_DX varchar(5) ;
	DECLARE V_SECOND_DX varchar(5) ;
	DECLARE V_THIRD_DX varchar(5) ;
	DECLARE V_FOURTH_DX varchar(5) ;
	DECLARE V_FIFTH_DX varchar(5) ;

	DECLARE V_FIRST_DS varchar(6) ;
	DECLARE V_SECOND_DS varchar(6) ;
	DECLARE V_THIRD_DS varchar(6) ;
	DECLARE V_FOURTH_DS varchar(6) ;
	DECLARE V_FIFTH_DS varchar(6) ;

	DECLARE V_SUM_DX varchar(8) ;
	DECLARE V_SUM_DS varchar(9) ;
	DECLARE V_SUM_LFH varchar(6) ;
	DECLARE V_SUMBEFORE5 varchar(15) ;
	DECLARE V_SUMMIDDLE5 varchar(15) ;
	DECLARE V_SUMAFTER5 varchar(15) ;

	DECLARE V_ORDERID  varchar(11);
  DECLARE V_BALLNUMBER  char(8);
  DECLARE V_BETTYPE  char(15);
  DECLARE V_COST decimal(16,1);
  DECLARE V_RATE  decimal(3,1);
	DECLARE no_more_orders INT DEFAULT 0;

	DECLARE order_csr CURSOR FOR select ORDER_ID,BALLNUMBER,BETTYPE,COST,RATE from RTS_BETORDER where FULL_PERIOD_NUMBER = PARSE ;
  DECLARE CONTINUE HANDLER FOR SQLSTATE'02000' SET no_more_orders=1;

  SELECT
  FIRST,SECOND,THIRD,FOURTH,FIFTH,FIRST_DX,SECOND_DX,THIRD_DX,FOURTH_DX,FIF_DX,FIRST_DS,SECOND_DS,THIRD_DS,FOURTH_DS,FIF_DS,SUM_DX,SUM_DS,SUM_LFH,BEFORESUM5,MIDDLESUM5,AFTERSUM5
  INTO
  V_FIRST,V_SECOND,V_THIRD,V_FOURTH,V_FIFTH,V_FIRST_DX,V_SECOND_DX,V_THIRD_DX,V_FOURTH_DX,V_FIFTH_DX,V_FIRST_DS,V_SECOND_DS,V_THIRD_DS,V_FOURTH_DS,V_FIFTH_DS,V_SUM_DX,V_SUM_DS,V_SUM_LFH,V_SUMBEFORE5,V_SUMMIDDLE5,V_SUMAFTER5
  FROM RTS_AWARDRESULT WHERE FULL_PERIOD_NUMBER=PARSE;

  OPEN order_csr;
  orderLoop:LOOP
  FETCH order_csr INTO V_ORDERID,V_BALLNUMBER,V_BETTYPE,V_COST,V_RATE;
  IF no_more_orders=1 THEN
    LEAVE orderLoop;
  ELSE
    orzx:BEGIN
    IF V_BALLNUMBER = '1' THEN
        IF (V_BETTYPE = 'double' OR  V_BETTYPE = 'single' )THEN
          IF V_BETTYPE = V_FIRST_DS THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 - V_COST;
          END IF;
        ELSEIF( V_BETTYPE = 'big' OR  V_BETTYPE = 'small' )THEN
          IF V_BETTYPE = V_FIRST_DX THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 - V_COST;
          END IF;
        ELSE
          IF V_BETTYPE =V_FIRST THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 - V_COST;
          END IF;
        END IF;
    ELSEIF V_BALLNUMBER = '2' THEN
        IF (V_BETTYPE = 'double' OR  V_BETTYPE = 'single' )THEN
          IF V_BETTYPE =V_SECOND_DS THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        ELSEIF(V_BETTYPE = 'big' OR  V_BETTYPE = 'small' )THEN
          IF V_BETTYPE =V_SECOND_DX THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 - V_COST;
          END IF;
        ELSE
          IF V_BETTYPE =V_SECOND THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        END IF;
    ELSEIF V_BALLNUMBER = '3' THEN
        IF (V_BETTYPE = 'double' OR  V_BETTYPE = 'single' )THEN
          IF V_BETTYPE =V_THIRD_DS THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        ELSEIF(V_BETTYPE = 'big' OR  V_BETTYPE = 'small' )THEN
          IF V_BETTYPE =V_THIRD_DX THEN
            SET @result :=  V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        ELSE
          IF V_BETTYPE =V_THIRD THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        END IF;
    ELSEIF V_BALLNUMBER = '4' THEN
        IF (V_BETTYPE = 'double' OR  V_BETTYPE = 'single' )THEN
          IF V_BETTYPE =V_FOURTH_DS THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        ELSEIF(V_BETTYPE = 'big' OR  V_BETTYPE = 'small' )THEN
          IF V_BETTYPE =V_FOURTH_DX THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        ELSE
          IF V_BETTYPE =V_FOURTH THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        END IF;
    ELSEIF V_BALLNUMBER = '5' THEN
        IF (V_BETTYPE = 'double' OR  V_BETTYPE = 'single' )THEN
          IF V_BETTYPE =V_FIFTH_DS THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        ELSEIF(V_BETTYPE = 'big' OR  V_BETTYPE = 'small' )THEN
          IF V_BETTYPE =V_FIFTH_DX THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        ELSE
          IF V_BETTYPE =V_FIFTH THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        END IF;
    ELSEIF V_BALLNUMBER = '6' THEN
        IF (V_BETTYPE = 'sumdouble' OR  V_BETTYPE = 'sumsingle' )THEN
          IF V_BETTYPE =V_SUM_DS THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        ELSEIF(V_BETTYPE = 'sumbig' OR  V_BETTYPE = 'sumsmall' )THEN
          IF V_BETTYPE =V_SUM_DX THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        ELSEIF(V_BETTYPE = 'dragon' OR  V_BETTYPE = 'tiger')THEN
          IF V_SUM_LFH='peace' THEN
            SET @result := 0 ;
          ELSEIF V_SUM_LFH = V_BETTYPE THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        ELSEIF(V_BETTYPE='peace')THEN
          IF V_SUM_LFH=V_BETTYPE THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        ELSEIF(V_BETTYPE = 'befleopard' OR  V_BETTYPE = 'befstraight' OR  V_BETTYPE = 'befpair'OR  V_BETTYPE = 'befhalfstraight'OR  V_BETTYPE = 'befmix6' )THEN
          IF V_BETTYPE = V_SUMBEFORE5 THEN
            SET @result :=  V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        ELSEIF(V_BETTYPE = 'midleopard' OR  V_BETTYPE = 'minstraight' OR  V_BETTYPE = 'minpair'OR  V_BETTYPE = 'minhalfstraight'OR  V_BETTYPE = 'minmix6' )THEN
          IF V_BETTYPE =V_SUMMIDDLE5 THEN
            SET @result :=  V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        ELSEIF(V_BETTYPE = 'aftleopard' OR  V_BETTYPE = 'aftstraight' OR  V_BETTYPE = 'aftpair'OR  V_BETTYPE = 'afthalfstraight'OR  V_BETTYPE = 'aftmix6' )THEN
          IF V_BETTYPE =V_SUMAFTER5 THEN
            SET @result := V_COST * (V_RATE -1);
          ELSE
            SET @result := 0 -V_COST;
          END IF;
        END IF;
    END IF;
    update RTS_BETORDER set BUNKORESULT =  @result  where ORDER_ID = V_ORDERID;
  END orzx;
END IF;
END LOOP;
CLOSE order_csr;
END