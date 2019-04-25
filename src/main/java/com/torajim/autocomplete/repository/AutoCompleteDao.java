package com.torajim.autocomplete.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AutoCompleteDao {
    private static final List<String> strings;
    static {
        strings = new ArrayList<>();
        strings.add("우리나라");
        strings.add("우리집");
        strings.add("우리는 중이에요");
        strings.add("우리나라대한민국");
        strings.add("나");
        strings.add("나비");
        strings.add("나무");
        strings.add("나혼자산다");
        strings.add("나의살던고향은");
        strings.add("고려은단");
        strings.add("운전면허");
        strings.add("스칼라는 재밌어");
        strings.add("하둡과 스파크를 활용한");
        strings.add("Lorem Ipsum has been the industry's standard dummy");
        strings.add("nd scrambled it to make a type specimen book. It");
        strings.add("typesetting, remaining essentially unchanged. It ");
        strings.add("sum passages, and more recently with desktop publi");
        strings.add("Contrary to popular belief, Lorem Ipsum is not sim");
        strings.add("professor at Hampden-Sydney College in Virginia, looked up one");
        strings.add("passage, and going through the cites of the word in");
        strings.add("comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum");
        strings.add("BC. This book is a treatise on the theory of ethics, very popu");
        strings.add("here are many variations of passages of Lorem Ipsum availa");
        strings.add("believable. If you are going to use a passage of Lorem Ips");
        strings.add("middle of text. All the Lorem Ipsum generators on the Intern");
        strings.add("tend to repeat predefined chunks as necessary, making this the");
        strings.add("first true generator on the Internet. It uses a dictionary of over 20");
        strings.add("he standard chunk of Lorem Ipsum used since the 1500s i");
        strings.add("1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are als");
        strings.add("reproduced in their exact original form, accompanied by English");
        strings.add("eadable content of a page when looking at its layout. The point");
    }

    public static List getStrings(final String input) {
        return strings.stream().filter(s -> s.toLowerCase().contains(input.toLowerCase())).collect(Collectors.toList());
    }
}