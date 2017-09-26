;;; solution for 150. Palindromic Numbers

;; A palindromic number is a number that is the same when written forwards or backwards (e.g., 3, 99, 14341).

;;Write a function which takes an integer n, as its only argument, and returns an increasing lazy sequence of all palindromic numbers that are not less than n.

;;The most simple solution will exceed the time limit!



;; given left hand side of the palindrom and info if palindrom has odd number of ciffern calcs this palindrom
(defn create-pal
  [left odd]
    (loop [res left
           left (if odd (quot left 10) left)]
        (if (= 0 left)
            res
            (recur (+ (* res 10) (mod left 10)) (quot left 10))
        )
    )
)


;;given a palindrom (not checked) gets info about palindrom:
;; left side of palindrom, whether palindrom is odd, the side of the right side
(defn pal-info
   [n]
   (loop [z n
          cnt 1
          right-factor 1]
          (if (< z 10)
            [(quot n right-factor) (odd? cnt) right-factor]
            (recur (quot z 10)
                 (inc cnt)
                 (if (odd? cnt) (* right-factor 10) right-factor))
          )
  )
)



(defn next-pal
  ([n] (let [[lhs odd right-size] (pal-info n)
             cand (create-pal lhs odd)]
            (if (>= cand n)
                (cons cand (next-pal lhs odd right-size))
                (next-pal lhs odd right-size)
            )
        )
  )
  ([lhs odd right-size]
    (let [next-lhs (inc lhs)
          [next-lhs next-odd next-rs] (if (< 9 (quot next-lhs right-size)); next size?
                                    [(if odd (quot next-lhs 10) next-lhs) (not odd) (if odd right-size (* right-size 10))]
                                    [next-lhs odd right-size])]
        (lazy-seq (cons (create-pal next-lhs next-odd) (next-pal next-lhs next-odd next-rs)))
    )
  )
)


(take 20 (next-pal 9))

(def __ next-pal)
; tests
(= (take 26 (__ 0))
   [0 1 2 3 4 5 6 7 8 9
    11 22 33 44 55 66 77 88 99
    101 111 121 131 141 151 161])

(= (take 16 (__ 162))
   [171 181 191 202
    212 222 232 242
    252 262 272 282
    292 303 313 323])

(= (take 6 (__ 1234550000))
   [1234554321 1234664321 1234774321
    1234884321 1234994321 1235005321])

(= (first (__ (* 111111111 111111111)))
   (* 111111111 111111111))

(= (set (take 199 (__ 0)))
   (set (map #(first (__ %)) (range 0 10000))))

(= true
   (apply < (take 6666 (__ 9999999))))

(= (nth (__ 0) 10101)
   9102019)
