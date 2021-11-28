package com.mygdx.game.event;

import com.mygdx.game.event.Compensation.Result;
import com.mygdx.game.event.Compensation.item.ItemFactory;
import com.mygdx.game.event.type.EventType;
import com.mygdx.game.player.PlayerStatus;
import com.mygdx.game.stage.StageNode;
import com.mygdx.game.textbox.TextBox;
import com.mygdx.game.utils.Pair;

import java.util.Random;

public enum Event implements EventInterface {
    REST(1) {
        @Override
        public void create(StageNode stageNode) {
            currentNode = new EventNode("", EventType.COMMAND_INPUT, stageNode);
        }
    },
    STAGE_ROOT(1)
    {
        @Override
        public void create(StageNode stageNode)
        {
            currentNode = new EventNode("(우르르쾅쾅).. 투명 드래곤이 울부짖었다.", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("....하지만.. 투명드래곤은 투명해서.. 보이지 않았다.", EventType.PRINTING);
            EventNode node2 = new EventNode("..여름이었다.", EventType.RESULT, new Result(ItemFactory.getItem("Sword")));
            EventNode node3 = new EventNode("'/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("던전 입구로 발을 옮겼다.", EventType.END_PRINTING);
            EventNode node_no = new EventNode("역시 던전 입구로 발을 옮겼다.", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node3);
            node3.link(node_yes, node_no);
        }
    },
    FIND_BOX_TYPE_GET_FIRE(1)
    {
        @Override
        public void create(StageNode stageNode) {
            Random random = new Random();

            currentNode = new EventNode("..어쩐지 불길한 분위기를 뿜어내는 상자가 보인다..", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("...열어볼까?", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("상자에서 불길이 치솟아 나를 덮쳤다.", EventType.RESULT,
                    new Result(new Pair<PlayerStatus, Float>(PlayerStatus.HP, -(float)random.nextInt(10))));
            EventNode node_yes_1 = new EventNode("", EventType.END_PRINTING);
            EventNode node_no = new EventNode("열어보지 않는게 좋겠어.", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },
    FIND_BOX_TYPE_GET_SWORD(1)
    {
        @Override
        public void create(StageNode stageNode) {
            Random random = new Random();

            currentNode = new EventNode("..어쩐지 불길한 분위기를 뿜어내는 상자가 보인다..", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("...열어볼까?", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("상자에서 아이템을 찾았다.", EventType.RESULT,
                    new Result(ItemFactory.getItem("Sword")));
            EventNode node_yes_1 = new EventNode("", EventType.END_PRINTING);
            EventNode node_no = new EventNode("열어보지 않는게 좋겠어.", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },
    FIND_WATER(1) {
        @Override
        public void create(StageNode stageNode) {

        }
    },
    FIND_LOW_HEAL(1) {
        @Override
        public void create(StageNode stageNode) {

        }
    },
    FIND_LOW_MP_HEAL(1) {
        @Override
        public void create(StageNode stageNode) {

        }
    };
    @Override
    public EventType update(float dt)
    {
        //if(eventOver) return EventType.DONE_END;

        EventType type = currentNode.eventType.update(currentNode);
        if (type == EventType.DONE_END)
        {
            TextBox.instance.setText("");
            //eventOver = true;
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
        return type;
    }

    protected EventNode currentNode;
    protected boolean eventOver = false;

    //
    private final int level;
    Event(int level) { this.level = level;}
    public int getLevel() {return level;}
    //
}
