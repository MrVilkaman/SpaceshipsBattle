package donnu.zolotarev.SpaceShip.Waves;

import android.graphics.Point;

public interface IAddedEnemy {
    public class AddedEnemyParam{
        private int kind;
        private Point StartPosition;
        private int startAngle = 180;

        public AddedEnemyParam(int kind, Point startPosition, int startAngle) {
            this.kind = kind;
            StartPosition = startPosition;
            this.startAngle = startAngle;
        }

        public AddedEnemyParam(int kind) {
            this.kind = kind;
        }

        public int getKind() {
            return kind;
        }

        public Point getStartPosition() {
            return StartPosition;
        }

        public int getStartAngle() {
            return startAngle;
        }

    }
    public void addEnemy(AddedEnemyParam param);
}
