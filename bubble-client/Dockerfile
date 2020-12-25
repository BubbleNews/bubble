FROM node:14-slim
WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY . .
RUN npm install -g serve
RUN npm install
EXPOSE 8080
RUN npm run build
CMD ["serve", "-s", "build", "-l", "8080"]