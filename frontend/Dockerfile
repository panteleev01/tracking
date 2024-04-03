# build stage
FROM node:21-alpine3.18

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# add `/usr/src/app/node_modules/.bin` to $PATH
ENV PATH /usr/src/app/node_modules/.bin:$PATH

# install and cache app dependencies
# COPY package.json /usr/src/app/package.json
COPY . .
RUN npm install
# RUN npm install -g @vue/cli
# start app 
CMD ["npm", "run", "dev"]