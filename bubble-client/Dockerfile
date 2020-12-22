FROM node:14-slim
WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY . .
RUN npm install
RUN npm run build
EXPOSE 8080
CMD ["npm", "start", "--port", "8080"]