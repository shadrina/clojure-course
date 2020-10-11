(ns clojure-course.lab-1-1-1-2)

(defn grow-one-perm
  [perm alphabet]
  (if (empty? alphabet)
    (list)
    (let [a (first alphabet), perm-last-letter (first perm), rest-result (grow-one-perm perm (rest alphabet))]
      (if (not= a perm-last-letter)
        (cons (cons a perm) rest-result)
        rest-result))))

(defn grow-one-perm-tailrec
  [perm alphabet acc]
  (if (empty? alphabet)
    acc
    (let [a (first alphabet), perm-last-letter (first perm)]
      (if (not= a perm-last-letter)
        (grow-one-perm-tailrec perm (rest alphabet) (cons (cons a perm) acc))
        (grow-one-perm-tailrec perm (rest alphabet) acc)))))

(defn grow-perms
  ([[perm & rest] alphabet]
   (let [grew-perm (grow-one-perm perm alphabet)]
     (if (empty? rest)
       grew-perm
       (concat grew-perm (grow-perms rest alphabet))))))

(defn grow-perms-tailrec
  ([[perm & rest] alphabet acc]
   (let [grew-perm (grow-one-perm perm alphabet)]
     (if (empty? rest)
       (concat acc grew-perm)
       (grow-perms-tailrec rest alphabet (concat acc grew-perm))))))

(defn grow-perms-n-times
  [perms n alphabet]
  (if (= n 0)
    perms
    (grow-perms-n-times (grow-perms-tailrec perms alphabet (list)) (- n 1) alphabet)))

(defn create-initial-perms
  [[a & rest-alphabet]]
  (if (empty? rest-alphabet)
    (list (list a))
    (cons (list a) (create-initial-perms rest-alphabet))))

(defn create-initial-perms-tailrec
  [[a & rest-alphabet] acc]
  (if (empty? rest-alphabet)
    (cons (list a) acc)
    (create-initial-perms-tailrec rest-alphabet (cons (list a) acc))))

(defn reverse-and-str-one-perm [l] (apply str (reverse l)))
(defn reverse-and-str-perms
  [[perm & rest]]
  (let [first-perm-result (reverse-and-str-one-perm perm)]
    (if (empty? rest)
      (list first-perm-result)
      (cons first-perm-result (reverse-and-str-perms rest)))))

(defn perms
  [alphabet n]
  (let [initial-perms (create-initial-perms alphabet)]
    (if (= n 1)
      initial-perms
      (reverse-and-str-perms (grow-perms-n-times initial-perms (- n 1) alphabet)))))

(println (perms (list "a" "b" "c") 6))
