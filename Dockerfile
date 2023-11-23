# ステージ1: Spring Boot APIサーバービルド (Gradleを使用)
FROM gradle:8.4.0-jdk17 AS backend-build
WORKDIR /app
COPY backend/build.gradle.kts /app
COPY backend/src /app/src
RUN gradle build -x test

# ステージ2: Reactフロントエンドビルド
FROM node:20.9.0 AS frontend-build
WORKDIR /app
COPY frontend/package.json frontend/package-lock.json* /app/
RUN npm ci
COPY frontend/ /app/
RUN npm run build

# 最終ステージ: イメージ組み立て
FROM amazoncorretto:17.0.9
# ソースコードそのままでLambdaを動かすためのコマンド
COPY --from=public.ecr.aws/awsguru/aws-lambda-adapter:0.7.1 /lambda-adapter /opt/extensions/lambda-adapter
# APIサーバーアプリケーションのコピー
COPY --from=backend-build /app/build/libs/*.jar /app/app.jar
# フロントエンドビルドのコピー
COPY --from=frontend-build /app/build /app/public
# データベースファイル（SQLiteなど）をコピーする場合はここに追加

# ポート設定
EXPOSE 8080

# 起動コマンド
CMD ["java", "-jar", "/app/app.jar"]
