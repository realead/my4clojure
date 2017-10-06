;;; solution for 82. Word chains

;;A word chain consists of a set of words ordered so that each word differs by only one letter from the words directly before and after it.
;; The one letter difference can be either an insertion, a deletion, or a substitution. Here is an example word chain:

;;cat -> cot -> coat -> oat -> hat -> hot -> hog -> dog

;;Write a function which takes a sequence of words, and returns true if they can be arranged into one continous word chain, and false if they cannot.


(defn diff
  [f s]
  (cond
      (or (empty? f) (empty? s)) (+ (count f) (count s))
      (= (first f) (first s))    (recur (rest f) (rest s))
      :else (+ 1 (min (diff2 f (rest s))   ;insert in first
                      (diff2 (rest f) s)   ;insert in second
                      (diff2 (rest f) (rest s))   ; no insert
                  )
            )
  )
)

(defn can-chain?
    [f s]
    (or (nil? f) (= 1 (diff f s)))
)

(defn word-chain?
  ([words] (word-chain?  nil words))
  ([last-word words]
    (if (empty? words)
        true
        (let [res (some true?
                     (map #(if (can-chain? last-word %)
                               (word-chain? % (disj words %))
                               false
                           )
                           words
                     )
                  )]
            (not (nil? res))
        )
    )
  )
)

(def __ word-chain?)

;;tests

(= true (__ #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}))
(= false (__ #{"cot" "hot" "bat" "fat"}))
(= false (__ #{"to" "top" "stop" "tops" "toss"}))
(= true (__ #{"spout" "do" "pot" "pout" "spot" "dot"}))
(= true (__ #{"share" "hares" "shares" "hare" "are"}))
(= false (__ #{"share" "hares" "hare" "are"}))


