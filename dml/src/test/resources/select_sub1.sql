
SELECT  AVG(TOTAL)
	FROM   (SELECT   PLAYERNO, SUM(AMOUNT) AS TOTAL
			FROM     PENALTIES
			GROUP BY PLAYERNO) AS TOTALS
	WHERE   PLAYERNO IN
	(SELECT   PLAYERNO
	 FROM     PLAYERS
	 WHERE    TOWN = 'Stratford' OR TOWN = 'Inglewood')




