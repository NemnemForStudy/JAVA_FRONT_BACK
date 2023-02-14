package chapter11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Example02 {

	public static void main(String[] args) {
		// Set Interface
		Set<String> hashset = new HashSet<String>();
		
		// set 구조에 데이터 추가
		hashset.add("apple");
		hashset.add("banana");
		hashset.add("apple");
		hashset.add("cucumber");
		hashset.add("apple");
		
		// set은 순서가 없기 때문에 index로 접근 X
		// Iterator로 set컬렉션에 접근
		Iterator<String> hashSetIterator = hashset.iterator();
		
		// Iterator의 hasNext() 메서드로 다음 요소가 있으면 반복
		while(hashSetIterator.hasNext()) {
			// Iterator의 next()메서드로 다음 요소를 가져옴
			System.out.println(hashSetIterator.next());
		}
		
		System.out.println("");
		
		Set<String> treeSet = new TreeSet<String>();
		
		treeSet.add("hello");
		treeSet.add("apple");
		treeSet.add("banana");
		treeSet.add("apple");
		treeSet.add("cucumber");
		treeSet.add("apple");
		
		
		Iterator<String> treeSetIterator = treeSet.iterator();
		while(treeSetIterator.hasNext()) {
			System.out.println(treeSetIterator.next());
		}
		
		// size()메서드로 길이 혹은 크기를 가져올 수 있음.
		System.out.println(treeSet.size());
		
		// List Interface
		// int같은게 아닌 integer 같은 것을 넣어줘야함
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(2);
		numbers.add(9);
		numbers.add(-1);
		numbers.add(50);
		
		// 특정한 위치에 특정 데이터를 넣음
		numbers.add(2, 38);
		
		// 특정한 위치에 데이터 변경
		numbers.set(3, 1);
		
		// 특정한 위치에 있는 데이터를 제거
		numbers.remove(3);
		
		// List는 순서가 존재하기 때문에 Interator를 사용하지 않아도 된다
		for(Integer number: numbers) {
			System.out.println(number);
		}
		
		List<String> arrayList = new ArrayList<String>();
		List<String> linkedList = new LinkedList<String>();
		
		long start = System.currentTimeMillis();
		
		for(int i = 0; i < 200000; i++) {
			arrayList.add(0, String.valueOf(i));
		}
		long end = System.currentTimeMillis();
		System.out.println("ArrayList 작업시간 : " + (end-start));
		
		start = System.currentTimeMillis();
		for(int i = 0; i < 200000; i++) {
			linkedList.add(0, String.valueOf(i));
		}
		end = System.currentTimeMillis();
		System.out.println("LinkedList 작업시간 : " + (end-start));
		
		// Map Interface
		// Map은 Generic을 2개 받는다.
		Map<String, String> hashMap = new HashMap<String, String>();
		
		// hashmap에 데이터를 추가할 때는 put!
		hashMap.put("key", "value");
		hashMap.put("name", "Nem nem");
		hashMap.put("address", "North London");
		
		// hashMap 에서 특정한 키에 해당하는 값을 가져옴
		System.out.println(hashMap);
		System.out.println(hashMap.get("name"));
		
		Set<String> keys = hashMap.keySet();
		Iterator<String> keyInterator = keys.iterator();
		
		while(keyInterator.hasNext()) {
			System.out.println(keyInterator.next());
		}
		if(hashMap.containsKey("age")) System.out.println(hashMap.get("age"));
	}

}
