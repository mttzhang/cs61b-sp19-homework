public class Palindrome {

    /**
     * return a Deque where the characters appear in the same order as in the given String.
     * eg. “persiflage”, return Deque with ‘p’ at the front, followed by ‘e’, and so forth.
     * @param word the given String
     * @return a deque consisting of the Character in the given String
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> chars = new LinkedListDeque<Character>();
        for (int index = 0; index < word.length(); index++) {
            chars.addLast(word.charAt(index));
        }
        return chars;
    }

    /**
     * return true if the given word is a palindrome, and false otherwise.
     * A palindrome is defined as a word that is the same whether it is read forwards or backwards.
     * For example “a”, “racecar”, and “noon” are all palindromes.
     * Any word of length 1 or 0 is a palindrome.
     * ‘A’ and ‘a’ should NOT be considered equal
     * @param word the given String word
     * @return
     */
    public boolean isPalindrome(String word) {
        if (word.length() < 2) {
            return true;
        }
        Deque<Character> chars = wordToDeque(word);
        while (chars.size() > 1) {
            if (!chars.removeFirst().equals(chars.removeLast())) {
                return false;
            }
        }
        return true;
    }

    /**
     * overloaded method for isPalindrome method
     * return true for off by one word like "flake"
     * Any word of length 1 or 0 is a palindrome.
     * ‘A’ and ‘a’ should NOT be considered equal
     * @param word given String word
     * @param cc a Character comparator
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() < 2) {
            return true;
        }
        Deque<Character> chars = wordToDeque(word);
        while (chars.size() > 1) {
            if (!cc.equalChars(chars.removeFirst(), chars.removeLast())) {
                return false;
            }
        }
        return true;
    }

}
