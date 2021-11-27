package com.mygdx.game.event;

import com.mygdx.game.event.Compensation.Result;
import com.mygdx.game.event.Compensation.item.ItemFactory;
import com.mygdx.game.event.type.EventType;
import com.mygdx.game.player.PlayerStatus;
import com.mygdx.game.textbox.TextBox;
import com.mygdx.game.utils.Pair;

import java.util.Random;

public enum Event implements EventInterface {

    STAGE_ROOT(1)
    {
        @Override
        public void create()
        {
            currentNode = new EventNode("던전의 스산한 기분이 들까말까", EventType.PRINTING); //root Node
            EventNode node1 = new EventNode("던전에 들어가시겠습니까?", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("System: 던전에 기쁜 마음으로 들어갑니다.", EventType.END_PRINTING);
            EventNode node_no = new EventNode("System: ...그래도 들어가야해요!", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node_yes, node_no);
        }
    },
    FIND_BOX_TYPE_GET_FIRE(1)
    {
        @Override
        public void create() {
            Random random = new Random();

            currentNode = new EventNode("..어쩐지 불길한 분위기를 뿜어내는 상자가 보인다..", EventType.PRINTING); //root Node
            EventNode node1 = new EventNode("...열어볼까?", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("System: 상자에서 불길이 치솟아 데미지를 입었습니다.", EventType.RESULT,
                    new Result(new Pair<PlayerStatus, Float>(PlayerStatus.HP, (float)random.nextInt(25))));
            EventNode node_no = new EventNode("System: 지나칩니다.", EventType.END_PRINTING);
        }
    },
    FIND_BOX_TYPE_GET_SWORD(1)
            {
                @Override
                public void create() {
                    Random random = new Random();

                    currentNode = new EventNode("..어쩐지 불길한 분위기를 뿜어내는 상자가 보인다..", EventType.PRINTING); //root Node
                    EventNode node1 = new EventNode("...열어볼까?", EventType.PRINTING); //root Node
                    EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

                    EventNode node_yes = new EventNode("System: 상자에서 아이템을 찾았습니다!", EventType.RESULT,
                            new Result(ItemFactory.getItem("Sword")));
                    EventNode node_no = new EventNode("System: 지나칩니다.", EventType.END_PRINTING);
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
