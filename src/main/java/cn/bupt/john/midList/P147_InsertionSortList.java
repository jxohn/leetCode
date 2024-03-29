package cn.bupt.john.midList;

/**
 * 解题思路 :
 * <p>
 *     1.需要遍历整个list的每个节点,
 *     2.在遍历过程中, 对遍历过的每个节点之前的节点都进行排序
 *     3.维护一个dummy节点, dummy.next是我们真正排好序的头结点.
 * </p>
 *
 * leetcode思路:
 * <p>
 *     1. 找到中间节点
 *     2. 逆序中间节点之后的list
 *     3. merge两个list
 * </p>
 */
public class P147_InsertionSortList {

    public static void main(String[] args) {
        P147_InsertionSortList solution = new P147_InsertionSortList();
        ListNode listNode = new ListNode(3);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode4 = new ListNode(4);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode4;
        ListNode listNode3 = solution.insertionSortList(listNode);
        System.out.println(listNode3);
    }

    // 这个是排好序的list的头结点dummy, first.next是真正的排好序的头结点
    private ListNode first = new ListNode(0);
    public ListNode insertionSortList(ListNode head) {
        first.next = head;
        while (head != null) {
            // 对每个node都需要进行一次插入, 为了避免node插入之后next节点被修改, 先存下next节点信息
            ListNode temp = head.next;
            // 插入节点
            insert(head);
            // 继续下一个节点的迭代
            head = temp;
        }
        // 返回排好序的list的第二个节点
        return first.next;
    }

    public void insert(ListNode node) {
        // 取第一个节点, 开始遍历
        ListNode temp = first;
        // 如果node就是下一个节点, 那么就将node节点的next节点设置为null
        if (node == temp.next) {
            temp.next.next = null;
            return;
        }
        while (temp.next != null) {
            // 如果node小于排好序的temp节点值, 则node需要插入到temp节点之前
            if(node.val <= temp.next.val) {
                ListNode next = temp.next;
                temp.next = node;
                node.next = next;
                break;
            }
            temp = temp.next;
        }
        // 遍历完排序好的node之后, 证明node是最大的, 需要将它加在排好序的node最后, 并且next节点设置为null
        if (temp.next == null) {
            temp.next = node;
            node.next = null;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
