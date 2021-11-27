package com.mygdx.game.event;

import com.mygdx.game.event.Compensation.Result;
import com.mygdx.game.event.Compensation.item.ItemFactory;
import com.mygdx.game.event.type.EventType;
import com.mygdx.game.player.PlayerStatus;
import com.mygdx.game.textbox.TextBox;
import com.mygdx.game.utils.Pair;

public enum Event implements EventInterface {

    STAGE_ROOT(1)
    {
        @Override
        public void create()
        {
            currentNode = new EventNode("{FASTER}???:흠.. 못보던 사람인데? 어디 사람이지..", EventType.PRINTING); //root Node
            EventNode node1 = new EventNode("???:그대는 무슨 일을 하는 사람인가?..", EventType.PRINTING);
            EventNode node2 = new EventNode("???:위험한 곳이니 조심하는 것이 좋을 걸세..", EventType.PRINTING);
            EventNode node3 = new EventNode("???:안쪽으로 들어가는건 권하지 않네만", EventType.PRINTING);
            EventNode node4 = new EventNode("???:왜냐니!! 당연히 위험하기 때문이지", EventType.PRINTING);
            EventNode node5 = new EventNode("???:..그래서 들어가겠는가?", EventType.PRINTING);
            EventNode node6 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node7 = new EventNode("아차차 뜬금없지만 칼을 받으시게..", EventType.RESULT, new Result(ItemFactory.getItem("Sword")));
            EventNode node9 = new EventNode("...돌아가게!", EventType.END_PRINTING);
            EventNode node8 = new EventNode("좋아! 무운을 빌도록하지..!!", EventType.PRINTING);
            EventNode node10 = new EventNode("System: 스텟을 얻었습니다.", EventType.RESULT, new Result(new Pair(PlayerStatus.DAMAGE, 10.0f)));
            EventNode node11 = new EventNode("System: 던전으로 진입합니다..", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node3);
            node3.link(node4);
            node4.link(node5);
            node5.link(node6);
            node6.link(node8, node7);
            node8.link(node10);
            node10.link(node11);
            node7.link(node9);

        }
    },
    FIND_FIRE(1)
    {
        @Override
        public void create() {
            currentNode = new EventNode("", EventType.PRINTING); //root Node
            EventNode node1 = new EventNode("???:그대는 무슨 일을 하는 사람인가?..", EventType.PRINTING);
            EventNode node2 = new EventNode("???:위험한 곳이니 조심하는 것이 좋을 걸세..", EventType.PRINTING);
            EventNode node3 = new EventNode("???:안쪽으로 들어가는건 권하지 않네만", EventType.PRINTING);
            EventNode node4 = new EventNode("???:왜냐니!! 당연히 위험하기 때문이지", EventType.PRINTING);
            EventNode node5 = new EventNode("???:..그래서 들어가겠는가?", EventType.PRINTING);
            EventNode node6 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);
        }
    },
    FIND_WATER(1) {
        @Override
        public void create() {

        }
    },
    FIND_LOW_HEAL(1) {
        @Override
        public void create() {

        }
    },
    FIND_LOW_MP_HEAL(1) {
        @Override
        public void create() {

        }
    };


    @Override
    public boolean update(float dt)
    {
        if(eventOver) return true;

        EventType type = currentNode.eventType.update(currentNode);
        if (type == EventType.DONE_END)
        {
            TextBox.instance.setText("");
            return true; //이벤트 종료
        }
        else if (type == EventType.DONE_LEFT)
        {
            currentNode = currentNode.leftNode;
            currentNode.eventType.setIsOneTime(false);
        }
        else if (type == EventType.DONE_RIGHT) {
            currentNode.eventType.setIsOneTime(false);
            currentNode = currentNode.rightNode;
        }
        return false;
    }

    protected EventNode currentNode;
    protected boolean eventOver = false;

    //
    private final int level;
    Event(int level) { this.level = level;}
    public int getLevel() {return level;}
    //
}
