package InterviewImportant.DS;



class Node{
    private int data;

    private Node left;
    private Node right;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

}
public class BinarySearchTree {


    /**
     * 二叉搜索树又称为二叉排序树，它可能是一棵空树，亦或是一棵具有以下性质的二叉树：
     *
     * 若它的左子树不为空，则左子树上所有结点的值都小于根结点的值。
     * 若它的右子树不为空，则右子树上所有结点的值都大于根结点的值。
     * 它的左右子树也分别为二叉搜索树。
     */

    //定义根结点
    private Node root;

    //插入元素
    public void add(int data) {
        Node node = new Node();
        node.setData(data);

        if (root == null) {
            root = node;
            return;
        }

        Node parentNode = root;

        while (true) {
            //如果插入的节点值小于父节点的值，则往左子树插入
            if (parentNode.getData() > data) {
                //如果左子树为空，则把插入节点作为左子树插入
                if (parentNode.getLeft() == null) {
                    parentNode.setLeft(node);
                    break;
                }


                parentNode = parentNode.getLeft();
                continue;
            }

            //如果待插入节点值大于父节点值，就往右子树找
            if (parentNode.getData() <= data) {

                //如果右子树为空，就把待插入节点当作右子树插入
                if (parentNode.getRight() == null) {
                    parentNode.setRight(node);
                    break;

                }
                parentNode = parentNode.getRight();
                continue;
            }
        }
    }

    //打印树的结构
    public void display(){

        if (root == null) {
            return;
        }
        //使用中序遍历
        inOrder(root);
    }

    //中序遍历
    private void inOrder(Node root) {

        if (root == null) {
            return;
        }

        inOrder(root.getLeft());
        System.out.println(root.getData()+",");
        inOrder(root.getRight());

    }



    //获取最大值,一直往右子树查找，直到右子树为空
    public void  getMax(){
        if (root == null) {
            return;
        }

        Node current = root;

        while (true) {

            if (current.getRight() == null) {
                System.out.println(current.getData());
                break;
            }
            current = current.getRight();
            continue;
        }
    }

    //获取最小值，一直往左子树查找，直到左子树为空
    public void getMin(){
        if (root == null) {
            return;
        }
        Node current = root;

        while (true) {
            if (current.getLeft() == null) {
                System.out.println(current.getData());
                break;

            }
            current = current.getLeft();
            continue;
        }

    }


    //删除某个节点

    /**
     * 待删除节点分为三种情况:
     * （1）待删除节点没有子节点，直接把该节点置为空
     * （2）待删除节点只有一个节点，直接用子节点代替它即可
     * （3）待删除节点有两个节点，
     */
    public void delete(int data){
        if (root == null) {
            return;
        }

        //待删除节点
        Node deleteNode = root;
        Node parentNode = null;

        //遍历找出待删除节点
        while (true) {
            if (deleteNode.getData() == data) {
                break;

            } else if (deleteNode.getData() > data) {
                parentNode = deleteNode;
                deleteNode = deleteNode.getLeft();

            }else {
                parentNode = deleteNode;
                deleteNode = deleteNode.getRight();

            }

            if (deleteNode == null) {
                System.out.println("没有该节点！");
                return;
            }
        }

        //叶子节点
        if (deleteNode.getLeft()==null||deleteNode.getRight()==null) {
            if (deleteNode.getLeft() == null && deleteNode.getRight() == null) {

                if (parentNode == null) {
                    root = null;
                } else {
                    if (parentNode.getLeft() == deleteNode) parentNode.setLeft(null);
                    else parentNode.setRight(null);
                }
            }
            //只有一个子节点，直接用子节点代替删除节点即可
            else {

                if (deleteNode.getLeft() != null) {
                    if (parentNode == null) {
                        root = deleteNode.getLeft();

                    } else {

                        if (parentNode.getLeft() == deleteNode) {
                            parentNode.setLeft(deleteNode.getLeft());
                        } else {
                            parentNode.setRight(deleteNode.getLeft());
                        }
                    }
                } else {

                    if (parentNode == null) {
                        root = deleteNode.getRight();

                    } else {
                        if (parentNode.getLeft() == deleteNode) parentNode.setLeft(deleteNode.getRight());
                        else parentNode.setRight(deleteNode.getRight());
                    }
                }
            }
        }
        //待删除节点，有两个子节点，需要找到后继节点
        else {
            //找出后继节点，即：大于删除节点的最小节点，或者，小于待删除节点的最大节点，两者都一样，
            //大于待删除节点的最小节点：待删除节点的右子节点的左子节点的左子节点的左子节点....
            //待删除节点的后继节点顶替待删除节点，后继节点的右子节点顶替后继节点，后继节点没有左子节点
            Node subNode = deleteNode.getRight();//后继节点
            Node subParentNode = deleteNode;//后继节点的父节点
            while (true) {

                if (subNode.getLeft() == null) {
                    break;
                }else {
                    subParentNode = subNode;
                    subNode = subParentNode.getLeft();

                }
            }
            //待删除节点的相关节点命名
            Node lchildNode = deleteNode.getLeft();
            Node rchildNode = deleteNode.getRight();

            //后继节点的相关节点命名
            Node srchildNode = subNode.getRight();//后继节点的右子节点
            //后继节点代替待删除节点
            subNode.setLeft(lchildNode);//后继节点的左子节点指向待删除节点的左子节点
            if (subParentNode!=deleteNode){
                subNode.setRight(rchildNode);//后继节点的右子节点指向待删除节点的右子节点，特殊情况：待删除节点就是后继节点
            }

            if (parentNode == null) {
                root=subNode;

            }else {
                if (parentNode.getLeft()==deleteNode){
                    parentNode.setLeft(subNode);
                }else {
                    parentNode.setRight(subNode);
                }
            }

            //后继节点的右子节点顶替后继节点，特殊情况：待删除的右子节点就是后继节点
            if (subParentNode != deleteNode) {
                subParentNode.setLeft(srchildNode);
            }

        }


    }


}
