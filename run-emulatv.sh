#!/bin/bash

# === CONFIGURATION ===
AGENT_DIR="./emulatv-agent"
AGENT_SCRIPT="index.js"
COMPOSE_FILE="docker-compose.yaml"
APP_URL="http://localhost/ui/"

GREEN="\033[0;32m"
YELLOW="\033[1;33m"
RED="\033[0;31m"
NC="\033[0m"

# === Start the Node agent ===
echo -e "${GREEN}➡️  Starting local Node agent...${NC}"
cd "$AGENT_DIR" || { echo -e "${RED}❌ Could not find agent directory"; exit 1; }
node "$AGENT_SCRIPT" &
AGENT_PID=$!

cd - > /dev/null || exit

echo -e "${GREEN}✅ Successfully started agent (PID: $AGENT_PID)${NC}"

# === Execute docker compose ===
echo -e "${GREEN}➡️  Starting docker containers...${NC}"
docker compose -f "$COMPOSE_FILE" up -d || { echo -e "${RED}❌ Error while running docker compose"; kill $AGENT_PID; exit 1; }

# === Open browser ===
echo -e "${GREEN}🌐 Opening of ${APP_URL} in browser...${NC}"

if command -v xdg-open &> /dev/null; then
  xdg-open "$APP_URL" >/dev/null 2>&1 &
elif command -v open &> /dev/null; then
  open "$APP_URL" >/dev/null 2>&1 &
else
  echo -e "${YELLOW}⚠️  Cannot automatically open browser. Manually open : ${APP_URL}${NC}"
fi

echo -e "${GREEN}✅ Everything is up !${NC}"
echo -e "${YELLOW}👉 Hit a key to stop...${NC}"
read -n 1 -s
echo -e "${YELLOW}🛑 Stopping processes...${NC}"

docker compose -f "$COMPOSE_FILE" down
kill $AGENT_PID 2>/dev/null

echo -e "${GREEN}✅ Everything has stopped properly.${NC}"
