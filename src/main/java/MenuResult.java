public class MenuResult {
        private Item item;
        private boolean exit;

        public MenuResult(Item item, boolean exit) {
            this.item = item;
            this.exit = exit;
        }

        public Item getItem() {
            return item;
        }

        public boolean isExit() {
            return exit;
        }
    }
