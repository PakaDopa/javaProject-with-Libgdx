package com.mygdx.game.event;

import com.mygdx.game.base.BaseEnemy;
import com.mygdx.game.enemy.EnemyFactory;
import com.mygdx.game.event.Compensation.Result;
import com.mygdx.game.event.Compensation.item.ItemFactory;
import com.mygdx.game.event.type.EventType;
import com.mygdx.game.player.PlayerStatus;
import com.mygdx.game.stage.StageNode;
import com.mygdx.game.textbox.TextBox;
import com.mygdx.game.textbox.TextInputBox;
import com.mygdx.game.utils.Pair;

import java.util.Random;

public enum Event implements EventInterface {
    REST(1) {
        @Override
        public void create(StageNode stageNode) {
            currentNode = new EventNode("", EventType.COMMAND_INPUT, stageNode);
        }
    },
    // 시작 스테이지 고정
    STAGE_ROOT(1)
    {
        @Override
        public void create(StageNode stageNode)
        {
            currentNode = new EventNode("던전 입구에 선 나는. 다시 한번 각오를 다짐하고", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("발걸음을 던전 안으로 옮겼다.", EventType.END_PRINTING);

            currentNode.link(node1);
        }
    },
    // 상황요소 이벤트
    FIND_BOX_TYPE_GET_FIRE(1)
    {
        @Override
        public void create(StageNode stageNode) {
            Random random = new Random();

            currentNode = new EventNode("..어쩐지 불길한 분위기를 뿜어내는 상자가 보인다..", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("...열어볼까?", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("상자에서 불길이 치솟아 나를 덮쳤다.", EventType.RESULT,
                    new Result(new Pair<PlayerStatus, Float>(PlayerStatus.HP, -(float)random.nextInt(5))));
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
    FIND_WATER_DROWN(1) {
    @Override
    public void create(StageNode stageNode) {
        Random random = new Random();

        currentNode = new EventNode("...(첨벙) .. 물웅덩이를 밟았다 ", EventType.PRINTING, stageNode); //root Node
        EventNode node1 = new EventNode("밟고 지나갈까?", EventType.PRINTING); //root Node
        EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

        EventNode node_yes = new EventNode("진흙에 빠져서 체력이 소모됐다.", EventType.RESULT,
                new Result(new Pair<PlayerStatus, Float>(PlayerStatus.HP, -(float)random.nextInt(10))));

        EventNode node_yes_1 = new EventNode("", EventType.END_PRINTING);
        EventNode node_no = new EventNode("다른 길로 돌아가는게 좋겠어.", EventType.END_PRINTING);

        currentNode.link(node1);
        node1.link(node2);
        node2.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },

    FIND_WATER_GET_HEAL(1)
    {
        @Override
        public void create(StageNode stageNode) {
            Random random = new Random();

            currentNode = new EventNode("...(첨벙) .. 물웅덩이가 길을 막는다 ", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("그냥 밟고 지나갈까?", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("물에서 회복능력을 발견했다.", EventType.RESULT,
                    new Result(new Pair<PlayerStatus, Float>(PlayerStatus.HP, (float)random.nextInt(10))));
            EventNode node_yes_1 = new EventNode("", EventType.END_PRINTING);
            EventNode node_no = new EventNode("다른 길로 돌아가는게 좋겠어.", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },
    LOOK_HEAL_TREE(1)
    {
        @Override
        public void create(StageNode stageNode) {
            Random random = new Random();

            currentNode = new EventNode("저 멀리 나무의 형상이 보인다.. ", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("근처로 가볼까?", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("나무에서 회복 능력이 발견됐다.", EventType.RESULT,
                    new Result(new Pair<PlayerStatus, Float>(PlayerStatus.HP, (float)random.nextInt(10))));
            EventNode node_yes_1 = new EventNode("", EventType.END_PRINTING);
            EventNode node_no = new EventNode("다른 길로 돌아가는게 좋겠어.", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },
    ROCK_SLIDE_AREA_DAMAGE(1)
    {
        @Override
        public void create(StageNode stageNode)
        {
            Random random = new Random();

            currentNode = new EventNode("... 낙석의 위험이 있는 지름길이 보인다..", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("...이 길을 지나갈까?", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("(악 ... ) 떨어지는 돌에 맞아 부상을 입었다.", EventType.RESULT,
                    new Result(new Pair<PlayerStatus, Float>(PlayerStatus.HP, -(float)random.nextInt(15))));
            EventNode node_yes_1 = new EventNode("", EventType.END_PRINTING);
            EventNode node_no = new EventNode("다른 길로 돌아가는게 좋겠어.", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },
    ROCK_SLIDE_AREA_GET(1)
    {
        @Override
        public void create(StageNode stageNode) {
            Random random = new Random();

            currentNode = new EventNode("... 낙석의 위험이 있는 지름길이 보인다..", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("...이 길을 지나갈까?", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("떨어지는 돌 사이에서 단단한 돌 방패를 얻었다.", EventType.RESULT,
                    new Result(ItemFactory.getItem("StoneShield")));
            EventNode node_yes_1 = new EventNode("", EventType.END_PRINTING);
            EventNode node_no = new EventNode("다른 길로 돌아가는게 좋겠어.", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },
    FINE_BONFIRE(1)
    {
        @Override
        public void create(StageNode stageNode)
        {
            Random random = new Random();

            currentNode = new EventNode("작은 불씨가 보이는듯 하다.. ", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("동굴 안이 너무 추워서 점점 지쳐가는거같아..", EventType.PRINTING);
            EventNode node2 = new EventNode("모닥불을 피워볼까?", EventType.PRINTING); //root Node
            EventNode node3 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("누군가가 머물고 간 흔적에서 모닥불을 피우고 체력을 보충했다.", EventType.RESULT,
                    new Result(new Pair<PlayerStatus, Float>(PlayerStatus.HP, (float)random.nextInt(15))));
            EventNode node_yes_1 = new EventNode("", EventType.END_PRINTING);
            EventNode node_no = new EventNode("더이상 체력을 더 소모하지 않는게 좋겠어.", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node3);
            node3.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },
    LOOK_THORNBUSH(1)
    {
        @Override
        public void create(StageNode stageNode)
        {
            Random random = new Random();

            currentNode = new EventNode("가시덤불때문에 길이 막혔다.. 조금 위험해보이는데.. ", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("그냥 지나가볼까?", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);


            EventNode node_yes = new EventNode("가시덤불 사이에서 갑옷처럼 보이는 옷을 얻었다.", EventType.RESULT,
                    new Result(ItemFactory.getItem("Armour")));
            EventNode node_yes_1 = new EventNode("", EventType.END_PRINTING);
            EventNode node_no = new EventNode("다른 길을 찾아보자.", EventType.END_PRINTING);
            currentNode.link(node1);
            node1.link(node2);
            node2.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },
    // 적 이벤트
    MEET_BAT_ATTACK(1)
    {
        @Override
        public void create(StageNode stageNode)
        {
            Random random = new Random();

            currentNode = new EventNode("박쥐 떼가 보인다. 찍찍 .. 찍... ", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("생각보다 규모가 작은데 공격해볼까?", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("숨어 있던 또 다른 박쥐 떼에게 습격을 당했다.", EventType.RESULT,
                    new Result(new Pair<PlayerStatus, Float>(PlayerStatus.HP, -(float)random.nextInt(10))));
            EventNode node_yes_1 = new EventNode("", EventType.END_PRINTING);
            EventNode node_no = new EventNode("무서우니까 그냥 돌아가는게 좋겠어.", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },
    MEET_SPIDER_GET_WEB(1)
    {
        @Override
        public void create(StageNode stageNode)
        {
            Random random = new Random();

            currentNode = new EventNode("..스스슥 ... 오싹한 기운이 돈다.. ", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("가서 뭐가 있는지 확인해볼까?", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("기습공격을 하려는 거미를 사뿐히 즈려밟았다.", EventType.RESULT,
                    new Result(ItemFactory.getItem("spiderWeb")));
            EventNode node_yes_1 = new EventNode("", EventType.END_PRINTING);
            EventNode node_no = new EventNode("무서우니까 그냥 가자.", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },

    MEET_ALLIGATOR(1)
    {
        @Override
        public void create(StageNode stageNode)
        {
            Random random = new Random();

            currentNode = new EventNode("... 동굴의 출구 쪽을 향해 가는 징검다리를 발견했다.. ", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("조금은 위험해보이지만... 돌아가기엔 너무 지쳤어", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("그냥 여기로 지나갈까?", EventType.PRINTING); //root Node
            EventNode node3 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("자고있는 악어를 밟고 지나가다 공격을 당했다... ", EventType.RESULT,
                    new Result(new Pair<PlayerStatus, Float>(PlayerStatus.HP, -(float)random.nextInt(15))));
            EventNode node_yes_1 = new EventNode("", EventType.END_PRINTING);
            EventNode node_no = new EventNode("그래도 너무 위험해보여. 다른 길을 찾아보자.", EventType.END_PRINTING);
            currentNode.link(node1);
            node1.link(node2);
            node2.link(node3);
            node3.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },
    ENCOUNTER_MURLOC(1)
    {
        @Override
        public void create(StageNode stageNode)
        {
            Random random = new Random();

            currentNode = new EventNode("갑자기 멀록이 튀어나왔다 !  ", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("싸워야 할까?", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("무기를 꺼내 들었다...!", EventType.BATTLE,
                    EnemyFactory.getEnemy("Murloc"));
            EventNode node_yes_1 = new EventNode("전투에서 승리했다!", EventType.END_PRINTING);


            EventNode node_no = new EventNode("..무서우니까 그냥 도망가자...", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },
    // 엑스칼리버

    NO_GET_EXCALIBUR(1)
    {
        @Override
        public void create(StageNode stageNode)
        {
            Random random = new Random();

            currentNode = new EventNode("..동굴의 반대편 구석에 반짝이는 무언가를 발견했다... ", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("가까이 가서 살펴보니 전설의 칼 엑스칼리버가 꽂혀있다...", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("엑스칼리버.. 한번 뽑아볼까?...", EventType.PRINTING); //root Node
            EventNode node3 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("검이 도저히 움직이지 않는다... 너무 많은 체력을 소모했다...", EventType.RESULT,
                    new Result(new Pair<PlayerStatus, Float>(PlayerStatus.HP, -(float)random.nextInt(20))));
            EventNode node_yes_1 = new EventNode("", EventType.END_PRINTING);
            EventNode node_no = new EventNode("이미 많이 지쳤어.. 도전해보지 않는게 좋겠어.", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node3);
            node3.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },
    GET_EXCALIBUR(1)
    {
        @Override
        public void create(StageNode stageNode)
        {
            Random random = new Random();

            currentNode = new EventNode("..동굴의 반대편 구석에 반짝이는 무언가를 발견했다... ", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("가까이 가서 살펴보니 전설의 칼 엑스칼리버가 꽂혀있다...", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("엑스칼리버.. 한번 뽑아볼까?...", EventType.PRINTING); //root Node
            EventNode node3 = new EventNode("System: '/yes' or '/no'", EventType.SELECT);

            EventNode node_yes = new EventNode("@@@@@@@전설의 검 엑스칼리버를 손에 얻었다.@@@@@@@@", EventType.RESULT,
                    new Result(ItemFactory.getItem("Excalibur")));
            EventNode node_yes_1 = new EventNode("", EventType.END_PRINTING);
            EventNode node_no = new EventNode("이미 많이 지쳤어.. 도전해보지 않는게 좋겠어.", EventType.END_PRINTING);

            currentNode.link(node1);
            node1.link(node2);
            node2.link(node3);
            node3.link(node_yes, node_no);

            node_yes.link(node_yes_1);
        }
    },
    ENDING(1)
    {
        @Override
        public void create(StageNode stageNode)
        {
            currentNode = new EventNode("여..기가 던전의 끝?", EventType.PRINTING, stageNode); //root Node
            EventNode node1 = new EventNode("생각보다 내부는 넓군", EventType.PRINTING); //root Node
            EventNode node2 = new EventNode("....돌아가자", EventType.END_PRINTING); //root Node

            currentNode.link(node1);
            node1.link(node2);
        }
    };
    @Override
    public EventType update(float dt)
    {

        EventType type = currentNode.eventType.update(currentNode);
        if (type == EventType.DONE_END)
        {
            TextBox.instance.setText("");
            currentNode.eventType.setIsOneTime(false);
            TextInputBox.instance.setDefault();
        }
        else if(type == EventType.PLAYER_DIE)
        {
            TextBox.instance.setText("");
            TextInputBox.instance.setDefault();
        }
        else if (type == EventType.DONE_LEFT)
        {
            currentNode = currentNode.leftNode;
            currentNode.eventType.setIsOneTime(false);
            TextInputBox.instance.setDefault();
        }
        else if (type == EventType.DONE_RIGHT) {
            currentNode = currentNode.rightNode;
            currentNode.eventType.setIsOneTime(false);
            TextInputBox.instance.setDefault();
        }
        return type;
    }

    protected EventNode currentNode;
    //
    private final int level;
    Event(int level) { this.level = level;}
    public int getLevel() {return level;}
    //
}
