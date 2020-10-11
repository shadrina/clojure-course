(ns clojure-course.lab-1-3)

(defn my-map
  [f coll]
  (reverse (reduce
    (fn [acc elem]
      (if (list? acc)
        (conj acc (f elem))
        (conj (list (f acc)) (f elem))))
    coll)))

(defn my-filter
  [p coll]
  (reverse (reduce
    (fn [acc elem]
      (if (list? acc)
        (if (p elem) (conj acc elem) acc)
        (let [ini (if (p acc) (list acc) (list))] (if (p elem) (conj ini elem) ini))))
    coll)))

(println (my-map (fn [x] (* x 3)) (range 5)))
(println (my-map inc [1 2 3 4 5]))
(println (my-filter (fn [x] (= (mod x 2) 0)) (range 6)))
(println (my-filter even? (range 10)))
