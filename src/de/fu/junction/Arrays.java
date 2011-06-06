/* Arrays.java / 1:12:37 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;


import java.util.Collection;
import java.util.Random;
import de.fu.junction.annotation.meta.Author;

/**
 *
 */
@Author("Julian Fleischer")
public class Arrays {

    /**
     * 
     * @param <C>
     * @param $class
     * @param $array
     * @return
     */
    public static <C extends Collection<Double>> C as(final Class<C> $class, final double[] $array) {
        try {
            C $c = $class.newInstance();
            for (double $i : $array) {
                $c.add(new Double($i));
            }
            return $c;
        } catch (Exception $exc) {
            return null;
        }
    }

    /**
     * 
     * @param <C>
     * @param $class
     * @param $array
     * @return
     */
    public static <C extends Collection<Integer>> C as(final Class<C> $class, final int[] $array) {
        try {
            C $c = $class.newInstance();
            for (int $i : $array) {
                $c.add(new Integer($i));
            }
            return $c;
        } catch (Exception $exc) {
            return null;
        }
    }

    /**
     * 
     * @param <C>
     * @param $class
     * @param $array
     * @return
     */
    public static <C extends Collection<Long>> C as(final Class<C> $class, final long[] $array) {
        try {
            C $c = $class.newInstance();
            for (long $i : $array) {
                $c.add(new Long($i));
            }
            return $c;
        } catch (Exception $exc) {
            return null;
        }
    }

    /**
     * 
     * @param <T>
     * @param <C>
     * @param $class
     * @param $array
     * @return
     */
    public static <T, C extends Collection<T>> C as(final Class<C> $class, final T... $array) {
        try {
            C $c = $class.newInstance();
            for (T $e : $array) {
                $c.add($e);
            }
            return $c;
        } catch (Exception $exc) {
            return null;
        }
    }

    /**
     * 
     * @param <T>
     * @param $array
     * @param $needle
     * @return
     */
    public static <T> boolean contains(final boolean[] $array, final boolean $needle) {
        for (boolean $e : $array) {
            if ($e == $needle) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param <T>
     * @param $array
     * @param $needle
     * @return
     */
    public static <T> boolean contains(final byte[] $array, final byte $needle) {
        for (byte $e : $array) {
            if ($e == $needle) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param <T>
     * @param $array
     * @param $needle
     * @return
     */
    public static <T> boolean contains(final char[] $array, final char $needle) {
        for (char $e : $array) {
            if ($e == $needle) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param <T>
     * @param $array
     * @param $needle
     * @return
     */
    public static <T> boolean contains(final double[] $array, final double $needle) {
        for (double $e : $array) {
            if ($e == $needle) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param <T>
     * @param $array
     * @param $needle
     * @return
     */
    public static <T> boolean contains(final int[] $array, final int $needle) {
        for (int $e : $array) {
            if ($e == $needle) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param <T>
     * @param $array
     * @param $needle
     * @return
     */
    public static <T> boolean contains(final long[] $array, final long $needle) {
        for (long $e : $array) {
            if ($e == $needle) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param <T>
     * @param $array
     * @param $needle
     * @return
     */
    public static <T> boolean contains(final T[] $array, final T $needle) {
        if ($needle == null) {
            for (T $e : $array) {
                if ($e == null) {
                    return true;
                }
            }
        } else {
            for (T $e : $array) {
                if ($needle.equals($e)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param $array
     */
    public static void shuffle(final byte[] $array) {
        Random $r = new Random(System.currentTimeMillis());
        for (int $i = 0; $i < $array.length; $i++) {
            byte $swap = $array[$i];
            int $j = $r.nextInt($array.length);
            $array[$i] = $array[$j];
            $array[$j] = $swap;
        }
    }

    /**
     * 
     * @param $array
     * @param $seed
     */
    public static void shuffle(final byte[] $array, final long $seed) {
        Random $r = new Random($seed);
        for (int $i = 0; $i < $array.length; $i++) {
            byte $swap = $array[$i];
            int $j = $r.nextInt($array.length);
            $array[$i] = $array[$j];
            $array[$j] = $swap;
        }
    }

    /**
     * 
     * @param $array
     */
    public static void shuffle(final char[] $array) {
        Random $r = new Random(System.currentTimeMillis());
        for (int $i = 0; $i < $array.length; $i++) {
            char $swap = $array[$i];
            int $j = $r.nextInt($array.length);
            $array[$i] = $array[$j];
            $array[$j] = $swap;
        }
    }

    /**
     * 
     * @param $array
     * @param $seed
     */
    public static void shuffle(final char[] $array, final long $seed) {
        Random $r = new Random($seed);
        for (int $i = 0; $i < $array.length; $i++) {
            char $swap = $array[$i];
            int $j = $r.nextInt($array.length);
            $array[$i] = $array[$j];
            $array[$j] = $swap;
        }
    }

    /**
     * 
     * @param $array
     */
    public static void shuffle(final double[] $array) {
        Random $r = new Random(System.currentTimeMillis());
        for (int $i = 0; $i < $array.length; $i++) {
            double $swap = $array[$i];
            int $j = $r.nextInt($array.length);
            $array[$i] = $array[$j];
            $array[$j] = $swap;
        }
    }

    /**
     * 
     * @param $array
     * @param $seed
     */
    public static void shuffle(final double[] $array, final long $seed) {
        Random $r = new Random($seed);
        for (int $i = 0; $i < $array.length; $i++) {
            double $swap = $array[$i];
            int $j = $r.nextInt($array.length);
            $array[$i] = $array[$j];
            $array[$j] = $swap;
        }
    }

    /**
     * 
     * @param $array
     */
    public static void shuffle(final float[] $array) {
        Random $r = new Random(System.currentTimeMillis());
        for (int $i = 0; $i < $array.length; $i++) {
            float $swap = $array[$i];
            int $j = $r.nextInt($array.length);
            $array[$i] = $array[$j];
            $array[$j] = $swap;
        }
    }

    /**
     * 
     * @param $array
     * @param $seed
     */
    public static void shuffle(final float[] $array, final long $seed) {
        Random $r = new Random($seed);
        for (int $i = 0; $i < $array.length; $i++) {
            float $swap = $array[$i];
            int $j = $r.nextInt($array.length);
            $array[$i] = $array[$j];
            $array[$j] = $swap;
        }
    }

    /**
     * 
     * @param $array
     */
    public static void shuffle(final int[] $array) {
        Random $r = new Random(System.currentTimeMillis());
        for (int $i = 0; $i < $array.length; $i++) {
            int $swap = $array[$i];
            int $j = $r.nextInt($array.length);
            $array[$i] = $array[$j];
            $array[$j] = $swap;
        }
    }

    /**
     * 
     * @param $array
     * @param $seed
     */
    public static void shuffle(final int[] $array, final long $seed) {
        Random $r = new Random($seed);
        for (int $i = 0; $i < $array.length; $i++) {
            int $swap = $array[$i];
            int $j = $r.nextInt($array.length);
            $array[$i] = $array[$j];
            $array[$j] = $swap;
        }
    }

    /**
     * 
     * @param $array
     */
    public static void shuffle(final long[] $array) {
        Random $r = new Random(System.currentTimeMillis());
        for (int $i = 0; $i < $array.length; $i++) {
            long $swap = $array[$i];
            int $j = $r.nextInt($array.length);
            $array[$i] = $array[$j];
            $array[$j] = $swap;
        }
    }

    /**
     * 
     * @param $array
     * @param $seed
     */
    public static void shuffle(final long[] $array, final long $seed) {
        Random $r = new Random($seed);
        for (int $i = 0; $i < $array.length; $i++) {
            long $swap = $array[$i];
            int $j = $r.nextInt($array.length);
            $array[$i] = $array[$j];
            $array[$j] = $swap;
        }
    }

    /**
     * 
     * @param <T>
     * @param $array
     */
    public static <T> void shuffle(final T[] $array) {
        Random $r = new Random(System.currentTimeMillis());
        for (int $i = 0; $i < $array.length; $i++) {
            T $swap = $array[$i];
            int $j = $r.nextInt($array.length);
            $array[$i] = $array[$j];
            $array[$j] = $swap;
        }
    }

    /**
     * 
     * @param <T>
     * @param $array
     * @param $seed
     */
    public static <T> void shuffle(final T[] $array, final long $seed) {
        Random $r = new Random($seed);
        for (int $i = 0; $i < $array.length; $i++) {
            T $swap = $array[$i];
            int $j = $r.nextInt($array.length);
            $array[$i] = $array[$j];
            $array[$j] = $swap;
        }
    }
}
