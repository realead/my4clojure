;;; Solution for 178. Best Hand

;; Following on from Recognize Playing Cards, determine the best poker hand that can be made with five cards. The hand rankings are listed below for your convenience.

;;    Straight flush: All cards in the same suit, and in sequence
;;    Four of a kind: Four of the cards have the same rank
;;    Full House: Three cards of one rank, the other two of another rank
;;    Flush: All cards in the same suit
;;    Straight: All cards in sequence (aces can be high or low, but not both at once)
;;    Three of a kind: Three of the cards have the same rank
;;    Two pair: Two pairs of cards have the same rank
;;    Pair: Two cards have the same rank
;;    High card: None of the above conditions are met


(doc group-by)
; reuse code from 128.

(defn recognize-card
      [card]
      (let [recognize-suit {\D :diamond \S :spade \H :heart \C :club}
            recognize-rank {\2 0 \3 1 \4 2 \5 3 \6 4 \7 5 \8 6 \9 7 \T 8 \J 9 \Q 10 \K 11 \A 12}]
            [(recognize-suit (first card)) (recognize-rank (second card))]
       )
 )

(defn straight?
  [cards]
  (let [vals (sort (map #(second %) cards))]
      (and (= 5 (count (distinct vals)))
           (or (= [vals] [0 1 2 3 12]); special case
               (= 4 (- (apply max vals) (apply min vals)))
           )
      )
  )
)

(defn flush?
    [cards]
    (let [suits (into #{}
                      (for [[s n] cards] s))]
        (= 1 (count suits))
    )
)

(defn max-of-a-kind
    [cards]
    (let [counts (reverse (sort (for [[k x] (group-by #(second %) cards)] (count x))))]
        [(first counts) (second counts)]
    )
)


(defn highest
   [cards]
   (let [cards (map recognize-card cards)
        [first-kind-count second-kind-count] (max-of-a-kind cards)]
       (cond
           (and (straight? cards) (flush? cards)) :straight-flush
           (= first-kind-count 4) :four-of-a-kind
           (and (= first-kind-count 3) (= second-kind-count 2)) :full-house
           (flush? cards) :flush
           (straight? cards) :straight
           (= first-kind-count 3) :three-of-a-kind
           (and (= first-kind-count 2) (= second-kind-count 2)) :two-pair
           (= first-kind-count 2) :pair
           :else :high-card
       )
   )
)


(def __ highest)

;;tests
(= :high-card (__ ["HA" "D2" "H3" "C9" "DJ"]))
(= :pair (__ ["HA" "HQ" "SJ" "DA" "HT"]))
(= :two-pair (__ ["HA" "DA" "HQ" "SQ" "HT"]))
(= :three-of-a-kind (__ ["HA" "DA" "CA" "HJ" "HT"]))
(= :straight (__ ["HA" "DK" "HQ" "HJ" "HT"]))
(= :straight (__ ["HA" "H2" "S3" "D4" "C5"]))
(= :flush (__ ["HA" "HK" "H2" "H4" "HT"]))
(= :full-house (__ ["HA" "DA" "CA" "HJ" "DJ"]))
(= :four-of-a-kind (__ ["HA" "DA" "CA" "SA" "DJ"]))
(= :straight-flush (__ ["HA" "HK" "HQ" "HJ" "HT"]))
