;;; Solution for 111. Crossword puzzle


;;Write a function that takes a string and a partially-filled crossword puzzle board, and determines if the input string can be legally placed onto the board.

;The crossword puzzle board consists of a collection of partially-filled rows. Empty spaces are denoted with an underscore (_), unusable spaces are denoted with a hash symbol (#),
; and pre-filled spaces have a character in place;
; the whitespace characters are for legibility and should be ignored.

;For a word to be legally placed on the board:
;- It may use empty spaces (underscores)
;- It may use but must not conflict with any pre-filled characters.
;- It must not use any unusable spaces (hashes).
;- There must be no empty spaces (underscores) or extra characters before or after the word (the word may be bound by unusable spaces though).
;- Characters are not case-sensitive.
;- Words may be placed vertically (proceeding top-down only), or horizontally (proceeding left-right only).


(defn fuzzy-equal?
  [word place]
    (if (not= (count word) (count place))
        false
        (loop [w word
               p place]
               (if (empty? w)
                   true
                   (if (contains? #{\_ (first w)} (first p))
                       (recur (rest w) (rest p))
                       false
                   )
                )
        )
    )
)


(defn can-place-in-line?
    [word line]
    (not (not-any? (partial fuzzy-equal? word)
           (clojure.string/split (apply str line) #"#"))
    )
)


(defn can-place-horizontally?
    [word board]
    (not (not-any? true?
             (map (partial can-place-in-line? word) board)
         )
    )
)

(defn can-place?
    [word board]
    (let [board (map #(filter (partial not= \space) %) board)];skip white spaces
        (or (can-place-horizontally? word board)
            (can-place-horizontally? word (apply map vector board));tranpose the board
        )
    )
)

(def __ can-place?)


;tests

(= true  (__ "the" ["_ # _ _ e"]))
(= false (__ "the" ["c _ _ _"
                    "d _ # e"
                    "r y _ _"]))

(= true  (__ "joy" ["c _ _ _"
                    "d _ # e"
                    "r y _ _"]))

(= false (__ "joy" ["c o n j"
                    "_ _ y _"
                    "r _ _ #"]))

(= true  (__ "clojure" ["_ _ _ # j o y"
                        "_ _ o _ _ _ _"
                        "_ _ f _ # _ _"]))
