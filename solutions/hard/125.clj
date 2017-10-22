;;;; Solution for 125. Gus' Guinundrum


;;Create a function of no arguments which returns a string that is an exact copy of the function itself.

;;Hint: read this if you get stuck (this question is harder than it first appears); but it's worth the effort to solve it independently if you can!

;;Fun fact: Gus is the name of the 4Clojure dragon.


(def a (str '(let [s "(let [s (apply str (concat (take 8 s) [(char 34)] s [(char 34) (char 93)] (drop 7 s))))"] (apply str (concat (take 8 s) [(char 34)] s [(char 34) (char 93)] (drop 7 s))))))
(def b (let [s "(let [s (apply str (concat (take 8 s) [(char 34)] s [(char 34) (char 93)] (drop 7 s))))"] (apply str (concat (take 8 s) [(char 34)] s [(char 34) (char 93)] (drop 7 s)))))

(= a b)
(println "a" a)
(println "b" b)


;test:
(= (str '(let [s "(let [s (apply str (concat (take 8 s) [(char 34)] s [(char 34) (char 93)] (drop 7 s))))"] (apply str (concat (take 8 s) [(char 34)] s [(char 34) (char 93)] (drop 7 s)))))
         (let [s "(let [s (apply str (concat (take 8 s) [(char 34)] s [(char 34) (char 93)] (drop 7 s))))"] (apply str (concat (take 8 s) [(char 34)] s [(char 34) (char 93)] (drop 7 s))))
)
