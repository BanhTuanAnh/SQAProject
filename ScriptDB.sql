USE [master]
GO
/****** Object:  Database [PTIT_SQA_EMS]    Script Date: 6/13/2020 8:40:45 PM ******/
CREATE DATABASE [PTIT_SQA_EMS]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PTIT_SQA_EMS', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.BTANH2020\MSSQL\DATA\PTIT_SQA_EMS.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PTIT_SQA_EMS_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.BTANH2020\MSSQL\DATA\PTIT_SQA_EMS_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [PTIT_SQA_EMS] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PTIT_SQA_EMS].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PTIT_SQA_EMS] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET ARITHABORT OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET RECOVERY FULL 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET  MULTI_USER 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PTIT_SQA_EMS] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PTIT_SQA_EMS] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'PTIT_SQA_EMS', N'ON'
GO
ALTER DATABASE [PTIT_SQA_EMS] SET QUERY_STORE = OFF
GO
USE [PTIT_SQA_EMS]
GO
/****** Object:  UserDefinedFunction [dbo].[FuncGetElectricAmount]    Script Date: 6/13/2020 8:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date, ,>
-- Description:	<Description, ,>
-- =============================================
CREATE FUNCTION [dbo].[FuncGetElectricAmount]
(
	@AccountObjectID int,
	@KWHNumber int
)
RETURNS decimal (25,4)
AS
BEGIN
	DECLARE @Amount decimal (25,4),
		 @Tax decimal (25,4)
	Declare @contractType int 
	select @contractType = ContractType from AccountObject where AccountObjectID = @AccountObjectID
	If(@contractType=0)
	begin
		-- Declare the return variable here
	Declare  @tblResult1 table(
		Taget nvarchar (255),
		Amount decimal(25,4),
		Method nvarchar(255),
		SortOrder int
	)
	Declare @tblElectricPrice table(
		[Level] int,
		UnitPrice Decimal(25,4),
		[Range] int,
		LevelName nvarchar(255)
	)
	insert into @tblElectricPrice
	(
		[Level] ,
		UnitPrice ,
		[Range],
		LevelName 
	)
	Values
	(
		1,1678,50,N'Bậc thang 1'
	),
		(
		2,1734,50,N'Bậc thang 2'
	),
		(
		3,2014,100,N'Bậc thang 3'
	),
		(
		4,2536,100,N'Bậc thang 4'
	),
		(
		5,2834,100,N'Bậc thang 5'
	),
		(
		6,2927,0,N'Bậc thang 6'
	)
		

	Set @Amount =0
	Set @Tax = 0

	DeClare @Level int,
			@UnitPrice Decimal(25,4) ,
			@Range int ,
			@LevelName nvarchar(255),
			@TempAmount  Decimal(25,4),
			@TempKWHNumber BigInt
	DECLARE @EmployeeID as nvarchar(255)
	DECLARE @Title as nvarchar(50)

	DECLARE EPrice_Cursor CURSOR FOR  
	SELECT [Level] ,
		UnitPrice ,
		[Range],
		LevelName  
	FROM @tblElectricPrice
 
	OPEN EPrice_Cursor;  
	FETCH NEXT FROM EPrice_Cursor INTO @Level,@UnitPrice,@Range,@LevelName;  
	WHILE @@FETCH_STATUS = 0  
	   BEGIN  
			

			Set @TempKWHNumber = Case When(@KWHNumber >= @Range and @Level <6 ) then
									@Range
									else
									@KWHNumber
									end
			Set @TempAmount = @TempKWHNumber * @UnitPrice
			Set @Amount = @Amount + @TempAmount
			Set @KWHNumber = @KWHNumber - @TempKWHNumber
			insert into @tblResult1
				(
					Taget,
					Method,
					Amount,
					SortOrder
				)
				Values(
					@LevelName,
					Cast((@TempKWHNumber ) As nvarchar(255))+' * '+Cast(@UnitPrice as nvarchar(255)),
					@TempAmount ,
					@Level +2
				)

		  FETCH NEXT FROM EPrice_Cursor INTO @Level,@UnitPrice,@Range,@LevelName;  
	   END;  
	CLOSE EPrice_Cursor;  
	DEALLOCATE EPrice_Cursor;
	
	Set @Amount = 1.1* @Amount  
	end
	else
	begin
				-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	Declare  @tblResult2 table(
		Taget nvarchar (255),
		Amount decimal(25,4),
		Method nvarchar(255)		
	)
	Declare @mulNum decimal(25,4) = 1
	Declare		@VoltType int 
	select @VoltType = VoltageType from AccountObject where AccountObjectID = @AccountObjectID
	
	if(@VoltType = 0 )
	begin
		Set @mulNum = 1685
	end
		if(@VoltType = 1 )
	begin
		Set @mulNum = 1611
	end
		if(@VoltType = 2 )
	begin
		Set @mulNum = 1555
	end
		if(@VoltType = 3 )
	begin
		Set @mulNum = 1536
	end
	set @Amount = @KWHNumber * @mulNum
	Set @Amount = 1.1*@Amount
	--------------------------------
	end
	
	-- Return the result of the function
	RETURN @Amount

END
GO
/****** Object:  UserDefinedFunction [dbo].[FuncGetElectricBussinessAmount]    Script Date: 6/13/2020 8:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date, ,>
-- Description:	<Description, ,>
-- =============================================
CREATE FUNCTION [dbo].[FuncGetElectricBussinessAmount]
(
	-- Add the parameters for the function here
	@KWHNumber int ,
	@VoltageType int

)
RETURNS Decimal(25,4)
AS
BEGIN

	-- Declare the return variable here

	DECLARE  @Amount Decimal(25,4),
			@MultiNumber money

	Set @MultiNumber = Case When (@VoltageType = 0) then 1685
							 When (@VoltageType = 1) then 1611
							  When (@VoltageType = 2) then 1555
							   When (@VoltageType = 3) then 1536
					End

	Set @Amount = 1.1*@MultiNumber * @KWHNumber

	
	-- Return the result of the function
	Return  @Amount
END
GO
/****** Object:  Table [dbo].[AccountObject]    Script Date: 6/13/2020 8:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AccountObject](
	[DateOfBirth] [datetime] NULL,
	[PhoneNumber] [nvarchar](255) NULL,
	[AccountObjectName] [nvarchar](100) NOT NULL,
	[AccountAddress] [nvarchar](255) NULL,
	[AccountObjectCode] [nvarchar](20) NULL,
	[AccountObjectID] [int] NOT NULL,
	[ClockID] [int] NULL,
	[AccountObjectType] [int] NOT NULL,
	[UserName] [nvarchar](255) NULL,
	[ContractType] [int] NULL,
	[Password] [nvarchar](255) NULL,
	[VoltageType] [int] NULL,
 CONSTRAINT [PK_AccountObject] PRIMARY KEY CLUSTERED 
(
	[AccountObjectID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Clock]    Script Date: 6/13/2020 8:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Clock](
	[ClockID] [int] NOT NULL,
	[ClockCode] [nvarchar](20) NOT NULL,
	[ClockName] [nvarchar](255) NULL,
 CONSTRAINT [PK_Clock] PRIMARY KEY CLUSTERED 
(
	[ClockID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ClockDetail]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ClockDetail](
	[Year] [int] NULL,
	[ClockIndexID] [int] NOT NULL,
	[Month] [int] NULL,
	[ClockIndex] [int] NULL,
	[CreatedDate] [date] NULL,
	[ClockID] [int] NOT NULL,
	[EmployeeID] [int] NULL,
	[IsLatestIndex] [bit] NULL,
	[PreClockIndexID] [int] NULL,
 CONSTRAINT [PK_ClockDetail] PRIMARY KEY CLUSTERED 
(
	[ClockIndexID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice](
	[State] [int] NULL,
	[CreatedDate] [date] NULL,
	[InvoiceCode] [nvarchar](20) NOT NULL,
	[TotalAmount] [decimal](18, 4) NULL,
	[CustomerID] [int] NOT NULL,
	[ClockIndexID] [int] NULL,
	[InvoiceID] [int] NOT NULL,
	[Month] [int] NULL,
	[Description] [nvarchar](255) NULL,
 CONSTRAINT [PK_Invoice] PRIMARY KEY CLUSTERED 
(
	[InvoiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'0376834697', N'btanh', N'Ha Noi', N'AO001', 1, NULL, 1, N'admin', 0, N'21345', NULL)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'0906181498', N'Minh Huế', N'Hà Nội', N'KH001', 2, 1, 0, NULL, 0, NULL, 2)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'0376834697', N'Phạm ngọc ánh', N'Vũng Tàu', N'KH002', 3, 2, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'', N'Test 4', N'', N'KH4', 4, 13, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'', N'Test 5', N'', N'KH5', 5, 14, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'uiuiudsfsdfididid01', N'Hoàng Mai', N'', N'KH8', 8, 17, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'035789111', N'AHA', N'quất lâm', N'KH9', 9, 18, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'', N'banh tuan anh', N'Ha noi', N'KH10', 10, 19, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'00999', N'123', N'', N'KH11', 11, 20, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'', N'ad', N'', N'KH12', 12, 21, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'', N'ad', N'', N'KH12', 13, 21, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'', N'ad', N'', N'KH12', 14, 21, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'', N'dd', N'', N'KH16', 16, 23, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'', N'Mai', N'', N'KH17', 17, 24, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'0353213asda', N'NVAnhacnccc', N'Hi', N'KH19', 19, 26, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'0312621425', N'abcdef', N'hanoi', N'KH20', 20, 27, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'0123', N'update Customer', N'Hải phòng', N'KH99', 21, 28, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'0123', N'test customer', N'Hà Nội', N'KH99', 22, 29, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'0123', N'test customer', N'Hà Nội', N'KH99', 23, 29, 0, NULL, 0, NULL, 0)
INSERT [dbo].[AccountObject] ([DateOfBirth], [PhoneNumber], [AccountObjectName], [AccountAddress], [AccountObjectCode], [AccountObjectID], [ClockID], [AccountObjectType], [UserName], [ContractType], [Password], [VoltageType]) VALUES (NULL, N'0123', N'test customer', N'Hà Nội', N'KH99', 24, 28, 0, NULL, 0, NULL, 0)
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (1, N'C001', N'Ð?ng h? 1')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (2, N'C002', N'Ð?ng h? 2')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (3, N'C003', N'Ð?ng h? 3')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (4, N'DH4', N'Đồng hồ 4')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (5, N'DH5', N'Đồng hồ 5')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (6, N'DH6', N'Đồng hồ 6')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (7, N'DH7', N'Đồng hồ 7')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (8, N'DH8', N'Đồng hồ 8')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (9, N'DH9', N'Đồng hồ 9')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (10, N'DH10', N'Đồng hồ 10')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (11, N'DH11', N'Đồng hồ 11')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (12, N'DH12', N'Đồng hồ 12')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (13, N'DH13', N'Đồng hồ 13')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (14, N'DH14', N'Đồng hồ 14')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (15, N'DH15', N'Đồng hồ 15')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (16, N'DH16', N'Đồng hồ 16')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (17, N'DH17', N'Đồng hồ 17')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (18, N'DH18', N'Đồng hồ 18')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (19, N'DH19', N'Đồng hồ 19')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (20, N'DH20', N'Đồng hồ 20')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (21, N'DH21', N'Đồng hồ 21')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (22, N'DH22', N'Đồng hồ 22')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (23, N'DH23', N'Đồng hồ 23')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (24, N'DH24', N'Đồng hồ 24')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (25, N'DH25', N'Đồng hồ 25')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (26, N'DH26', N'Đồng hồ 26')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (27, N'DH27', N'Đồng hồ 27')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (28, N'', N'Đồng hồ 28')
INSERT [dbo].[Clock] ([ClockID], [ClockCode], [ClockName]) VALUES (29, N'0001', N'Đồng hồ 29')
INSERT [dbo].[ClockDetail] ([Year], [ClockIndexID], [Month], [ClockIndex], [CreatedDate], [ClockID], [EmployeeID], [IsLatestIndex], [PreClockIndexID]) VALUES (2020, 1, 1, 1000, CAST(N'2020-03-10' AS Date), 2, 1, 0, NULL)
INSERT [dbo].[ClockDetail] ([Year], [ClockIndexID], [Month], [ClockIndex], [CreatedDate], [ClockID], [EmployeeID], [IsLatestIndex], [PreClockIndexID]) VALUES (2020, 2, 2, 1010, CAST(N'2020-03-10' AS Date), 2, 1, 0, 1)
INSERT [dbo].[ClockDetail] ([Year], [ClockIndexID], [Month], [ClockIndex], [CreatedDate], [ClockID], [EmployeeID], [IsLatestIndex], [PreClockIndexID]) VALUES (2020, 3, 3, 1020, CAST(N'2020-03-10' AS Date), 2, 1, 0, 2)
INSERT [dbo].[ClockDetail] ([Year], [ClockIndexID], [Month], [ClockIndex], [CreatedDate], [ClockID], [EmployeeID], [IsLatestIndex], [PreClockIndexID]) VALUES (2019, 4, 1, 2000, CAST(N'2019-01-08' AS Date), 1, 1, 0, NULL)
INSERT [dbo].[ClockDetail] ([Year], [ClockIndexID], [Month], [ClockIndex], [CreatedDate], [ClockID], [EmployeeID], [IsLatestIndex], [PreClockIndexID]) VALUES (2019, 5, 2, 2010, CAST(N'2019-01-08' AS Date), 1, NULL, 1, 4)
INSERT [dbo].[ClockDetail] ([Year], [ClockIndexID], [Month], [ClockIndex], [CreatedDate], [ClockID], [EmployeeID], [IsLatestIndex], [PreClockIndexID]) VALUES (2020, 6, 4, 1040, CAST(N'2020-03-10' AS Date), 2, NULL, 1, 3)
INSERT [dbo].[ClockDetail] ([Year], [ClockIndexID], [Month], [ClockIndex], [CreatedDate], [ClockID], [EmployeeID], [IsLatestIndex], [PreClockIndexID]) VALUES (2020, 21, 2, 2020, CAST(N'2020-04-09' AS Date), 1, NULL, 0, 1)
INSERT [dbo].[ClockDetail] ([Year], [ClockIndexID], [Month], [ClockIndex], [CreatedDate], [ClockID], [EmployeeID], [IsLatestIndex], [PreClockIndexID]) VALUES (2020, 22, 5, 1234, CAST(N'2020-05-25' AS Date), 19, NULL, 0, NULL)
INSERT [dbo].[ClockDetail] ([Year], [ClockIndexID], [Month], [ClockIndex], [CreatedDate], [ClockID], [EmployeeID], [IsLatestIndex], [PreClockIndexID]) VALUES (2020, 23, 5, 1234, CAST(N'2020-05-25' AS Date), 19, NULL, 0, NULL)
INSERT [dbo].[ClockDetail] ([Year], [ClockIndexID], [Month], [ClockIndex], [CreatedDate], [ClockID], [EmployeeID], [IsLatestIndex], [PreClockIndexID]) VALUES (2020, 24, 5, 19000, CAST(N'2020-05-26' AS Date), 18, NULL, 0, NULL)
INSERT [dbo].[ClockDetail] ([Year], [ClockIndexID], [Month], [ClockIndex], [CreatedDate], [ClockID], [EmployeeID], [IsLatestIndex], [PreClockIndexID]) VALUES (2020, 25, 6, 1901, CAST(N'2020-05-26' AS Date), 18, NULL, 0, 24)
INSERT [dbo].[ClockDetail] ([Year], [ClockIndexID], [Month], [ClockIndex], [CreatedDate], [ClockID], [EmployeeID], [IsLatestIndex], [PreClockIndexID]) VALUES (2020, 26, 3, 1900, CAST(N'2020-05-26' AS Date), 1, NULL, 0, 2)
INSERT [dbo].[ClockDetail] ([Year], [ClockIndexID], [Month], [ClockIndex], [CreatedDate], [ClockID], [EmployeeID], [IsLatestIndex], [PreClockIndexID]) VALUES (2020, 27, 4, 1990, CAST(N'2020-05-26' AS Date), 2, NULL, 0, 3)
ALTER TABLE [dbo].[AccountObject] ADD  CONSTRAINT [DF_AccountObject_AccountObjectType]  DEFAULT ((0)) FOR [AccountObjectType]
GO
ALTER TABLE [dbo].[AccountObject] ADD  CONSTRAINT [DF_AccountObject_ContractType]  DEFAULT ((0)) FOR [ContractType]
GO
ALTER TABLE [dbo].[AccountObject] ADD  CONSTRAINT [DF_AccountObject_VoltageType]  DEFAULT ((0)) FOR [VoltageType]
GO
ALTER TABLE [dbo].[ClockDetail] ADD  CONSTRAINT [DF_ClockDetail_IsLatestIndex]  DEFAULT ((0)) FOR [IsLatestIndex]
GO
ALTER TABLE [dbo].[AccountObject]  WITH CHECK ADD  CONSTRAINT [FK_AccountObject_Clock] FOREIGN KEY([ClockID])
REFERENCES [dbo].[Clock] ([ClockID])
GO
ALTER TABLE [dbo].[AccountObject] CHECK CONSTRAINT [FK_AccountObject_Clock]
GO
ALTER TABLE [dbo].[ClockDetail]  WITH CHECK ADD  CONSTRAINT [FK_ClockDetail_AccountObject] FOREIGN KEY([EmployeeID])
REFERENCES [dbo].[AccountObject] ([AccountObjectID])
GO
ALTER TABLE [dbo].[ClockDetail] CHECK CONSTRAINT [FK_ClockDetail_AccountObject]
GO
ALTER TABLE [dbo].[ClockDetail]  WITH CHECK ADD  CONSTRAINT [FK_ClockDetail_Clock] FOREIGN KEY([ClockID])
REFERENCES [dbo].[Clock] ([ClockID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ClockDetail] CHECK CONSTRAINT [FK_ClockDetail_Clock]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_AccountObject] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[AccountObject] ([AccountObjectID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_AccountObject]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_ClockDetail] FOREIGN KEY([ClockIndexID])
REFERENCES [dbo].[ClockDetail] ([ClockIndexID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_ClockDetail]
GO
/****** Object:  StoredProcedure [dbo].[ProCheckLoginAccount]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- ProCheckLoginAccount 'admin','21345'
-- =============================================
CREATE PROCEDURE [dbo].[ProCheckLoginAccount] 
	@UserName Nvarchar(255),
	@PassWord Nvarchar(255)
AS
BEGIN
	Declare @now DateTime 
	Select @now = GetDate()
	SELECT top(1)
		[AccountObjectID],
		[AccountObjectCode],
		 [AccountObjectName],
			[PhoneNumber],
		  [AccountAddress]
	  FROM [dbo].[AccountObject]
	  WHERE [AccountObjectType] =1
	  AND UserName like @UserName
	  And Password like @PassWord
END
GO
/****** Object:  StoredProcedure [dbo].[ProDeleteClockDetailByID]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
create PROCEDURE [dbo].[ProDeleteClockDetailByID]
	@ID int
AS
BEGIN

	if(Exists (select * from ClockDetail where ClockDetail.ClockIndexID = @ID))
	begin
		delete ClockDetail where ClockIndexID = @ID
		Select 1 as RowAffacted
		return
	end
	Select 0 as RowAffacted
	return
END
GO
/****** Object:  StoredProcedure [dbo].[ProDeleteCustomerByID]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ProDeleteCustomerByID]
	@CustomerID int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	if(Exists (select * from AccountObject AO where AO.AccountObjectID = @CustomerID))
	begin
		delete AccountObject where AccountObjectID = @CustomerID
		Select 1 as RowAffacted
		return
	end
	Select 0 as RowAffacted
	return
END
GO
/****** Object:  StoredProcedure [dbo].[ProGetAllCustomers]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ProGetAllCustomers] 
	
AS
BEGIN
		Select 
		AO.AccountObjectID,
		AO.AccountObjectCode,
		AO.AccountObjectName,
		Case when AO.ContractType = 0 then N'Điện sinh hoạt'
		else N'Điện sản xuất'
		end as ContractType
		,
		AO.PhoneNumber,
		AO.AccountAddress,
		C.ClockCode
		from AccountObject AO
		inner join Clock C on C.ClockID = AO.ClockID
		where AO.AccountObjectType=0
END
GO
/****** Object:  StoredProcedure [dbo].[ProGetAllInvoices]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ProGetAllInvoices] 
	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.

	select I.InvoiceID,
			N'Tháng '+I.[Month] as [Month] ,
			I.CreatedDate ,
			I.InvoiceCode,
			AO.AccountObjectName,
			I.TotalAmount,
			I.[Description],
			AO.AccountObjectID
			from Invoice I
			Inner join AccountObject AO on I.CustomerID = AO.AccountObjectID
END
GO
/****** Object:  StoredProcedure [dbo].[ProGetBussinessCaculateResult]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ProGetBussinessCaculateResult] 
	@KWHNumber BigINt,
	@VoltType int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	Create table #tblResult(
		Taget nvarchar (255),
		Amount decimal(25,4),
		Method nvarchar(255)		
	)
	Declare @mulNum decimal(25,4) = 1
	Declare @amount decimal(25,4)
	Declare @tax Decimal(25,4)

	if(@VoltType = 0 )
	begin
		Set @mulNum = 1685
	end
		if(@VoltType = 1 )
	begin
		Set @mulNum = 1611
	end
		if(@VoltType = 2 )
	begin
		Set @mulNum = 1555
	end
		if(@VoltType = 3 )
	begin
		Set @mulNum = 1536
	end
	set @amount = @KWHNumber * @mulNum
	Set @tax = @amount * 0.1
	insert into #tblResult
	(
		Taget,
		Method,
		Amount
	)
	Values
		(
		N'Tiền điện',
		''	,
		@amount
	),
	
		(
		N'Thuế',
		N'Tiền điện * 0.1'	,
		@tax
	),
	(
		
		N'Tổng',
		N'Tiền điện + Thuế'	,
		@amount+ @tax
	),
	(
		N'Giờ BT',
		Cast(@KWHNumber As nvarchar(255))+' * '+Cast(@mulNum as nvarchar(100)),
		@amount
	)
	select 
	 R.Taget,
	 R.Method,
	 R.Amount
	from #tblResult R
END
GO
/****** Object:  StoredProcedure [dbo].[ProGetClockDetailByID]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ProGetClockDetailByID]
	@ID int
AS
BEGIN
	select CD.ClockIndexID, C.ClockCode,CD.CreatedDate,CD.Month,CD.Year,CD.ClockIndex from ClockDetail CD inner join Clock C on C.ClockID = CD.ClockID where ClockIndexID = @ID
END
GO
/****** Object:  StoredProcedure [dbo].[ProGetClockDetails]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- ProGetClockDetails 2
-- =============================================
CREATE PROCEDURE [dbo].[ProGetClockDetails] 
	@CustomerID int
AS
BEGIN
	declare @temp1 table(
		ClockIndexID int,
		[Month] int,
		[Year] int,
		ClockIndex int,
		CreateDate DateTime,
		ClockID int,
		EmployeeID int ,
		PreClockIndexID int
	)
	insert @temp1 
	select CD.ClockIndexID,
	CD.[Month],
	CD.[Year],
	CD.ClockIndex,
	CD.CreatedDate,
	CD.ClockID,
	CD.EmployeeID,
	CD.PreClockIndexID

	from ClockDetail CD
	inner join AccountObject AO on @CustomerID = AO.AccountObjectID And AO.ClockID = CD.ClockID

	declare @tblResult table(
		ClockIndexID int,
		[Period] Nvarchar(255),
		ClockIndex int,
		CreatedDate DateTime,
		ClockID int,
		PreClockIndex int,
		KWHNumber int ,
		[State] nvarchar(255)
	)
	insert @tblResult
	select
	CD.ClockIndexID,
	N'Tháng '+Cast(CD.[Month] As nvarchar(100) ) +'/'+Cast(CD.[Year] As nvarchar(100) ) As [Period] ,
	CD.ClockIndex,
	CD.CreatedDate,
	CD.ClockID,
	T1.ClockIndex As PreClockIndex ,
	IsNull(CD.ClockIndex - T1.ClockIndex,0) As KWHNumber,
	Case when (I.State is null) then N'Chưa lập hóa đơn'
		when (I.State =0 ) then N'Chưa thanh toán'
		when (I.State = 1 ) then N'Đã thanh toán'
	end As [State]
	from ClockDetail CD
	inner join AccountObject AO on @CustomerID = AO.AccountObjectID And AO.ClockID = CD.ClockID
	left join @temp1 T1 on CD.PreClockIndexID = T1.ClockIndexID
	left join Invoice I on CD.ClockIndexID = I.ClockIndexID And I.CustomerID = @CustomerID

	select * from @tblResult
END
GO
/****** Object:  StoredProcedure [dbo].[ProGetCommonCaculateResult]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ProGetCommonCaculateResult]
	@KWHNumber BigINt
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	Create table #tblResult(
		Taget nvarchar (255),
		Amount decimal(25,4),
		Method nvarchar(255),
		SortOrder int
	)
	Create table #tblElectricPrice(
		[Level] int,
		UnitPrice Decimal(25,4),
		[Range] int,
		LevelName nvarchar(255)
	)
	insert into #tblElectricPrice
	(
		[Level] ,
		UnitPrice ,
		[Range],
		LevelName 
	)
	Values
	(
		1,1678,50,N'Bậc thang 1'
	),
		(
		2,1734,50,N'Bậc thang 2'
	),
		(
		3,2014,100,N'Bậc thang 3'
	),
		(
		4,2536,100,N'Bậc thang 4'
	),
		(
		5,2834,100,N'Bậc thang 5'
	),
		(
		6,2927,0,N'Bậc thang 6'
	)
		
	DECLARE @Amount decimal (25,4),
			@Tax Decimal (25,4)
	Set @Amount =0
	Set @Tax = 0

	DeClare @Level int,
			@UnitPrice Decimal(25,4) ,
			@Range int ,
			@LevelName nvarchar(255),
			@TempAmount  Decimal(25,4),
			@TempKWHNumber BigInt
	DECLARE @EmployeeID as nvarchar(255)
	DECLARE @Title as nvarchar(50)

	DECLARE EPrice_Cursor CURSOR FOR  
	SELECT [Level] ,
		UnitPrice ,
		[Range],
		LevelName  
	FROM #tblElectricPrice
 
	OPEN EPrice_Cursor;  
	FETCH NEXT FROM EPrice_Cursor INTO @Level,@UnitPrice,@Range,@LevelName;  
	WHILE @@FETCH_STATUS = 0  
	   BEGIN  
			

			Set @TempKWHNumber = Case When(@KWHNumber >= @Range and @Level <6 ) then
									@Range
									else
									@KWHNumber
									end
			Set @TempAmount = @TempKWHNumber * @UnitPrice
			Set @Amount = @Amount + @TempAmount
			Set @KWHNumber = @KWHNumber - @TempKWHNumber
			insert into #tblResult
				(
					Taget,
					Method,
					Amount,
					SortOrder
				)
				Values(
					@LevelName,
					Cast((@TempKWHNumber ) As nvarchar(255))+' * '+Cast(@UnitPrice as nvarchar(255)),
					@TempAmount ,
					@Level +2
				)

		  FETCH NEXT FROM EPrice_Cursor INTO @Level,@UnitPrice,@Range,@LevelName;  
	   END;  
	CLOSE EPrice_Cursor;  
	DEALLOCATE EPrice_Cursor;  

	Set @Tax = 0.1 * @Amount
	insert into #tblResult
	(
		Taget,
		Method,
		Amount,
		SortOrder
	)
	Values
		(
		N'Tiền điện',
		''	,
		@Amount,
		0
	),
	
		(
		N'Thuế',
		N'Tiền điện * 0.1'	,
		@tax,
		1
	),
	(
		N'Tổng',
		N'Tiền điện + Thuế'	,
		@Amount+ @Tax,
		2
	)
	select 
	 R.Taget,
	 R.Method,
	 R.Amount
	from #tblResult R order by R.SortOrder
END
GO
/****** Object:  StoredProcedure [dbo].[ProGetCustomerByCode]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
create PROCEDURE [dbo].[ProGetCustomerByCode]
	@Code nvarchar(max)
AS
BEGIN
	select top 1 CD.*,C.ClockCode from AccountObject CD inner join Clock C on C.ClockID = CD.ClockID where CD.AccountObjectCode = @Code
END

GO
/****** Object:  StoredProcedure [dbo].[ProGetCustomerByID]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ProGetCustomerByID]
	@ID int
AS
BEGIN
	select CD.*,C.ClockCode from AccountObject CD inner join Clock C on C.ClockID = CD.ClockID where CD.AccountObjectID = @ID
END
GO
/****** Object:  StoredProcedure [dbo].[ProGetElectricIndexByCustomer]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- ProGetClockDetails 2
-- =============================================
CREATE PROCEDURE [dbo].[ProGetElectricIndexByCustomer] 
	@CustomerID int
AS
BEGIN
	declare @tblResult table(
		ClockIndexID int,
		[Period] Nvarchar(255),
		ClockIndex int,
		CreatedDate DateTime,
		ClockID int,
		PreClockIndex int,
		KWHNumber int ,
		[State] nvarchar(255),
		Amount Decimal(25,4)
	)
	insert @tblResult
	select
	CD.ClockIndexID,
	N'Tháng '+Cast(CD.[Month] As nvarchar(100) ) +'/'+Cast(CD.[Year] As nvarchar(100) ) As [Period] ,
	CD.ClockIndex,
	CD.CreatedDate,
	CD.ClockID,
	T1.ClockIndex As PreClockIndex ,
	IsNull(CD.ClockIndex - T1.ClockIndex,0) As KWHNumber,
	Case when (I.State is null) then N'Chưa lập hóa đơn'
		when (I.State =0 ) then N'Chưa thanh toán'
		when (I.State = 1 ) then N'Đã thanh toán'
	end As [State],
	0 As ElectricAmount
	
	from ClockDetail CD
	inner join AccountObject AO on @CustomerID = AO.AccountObjectID And AO.ClockID = CD.ClockID
	left join ClockDetail T1 on CD.PreClockIndexID = T1.ClockIndexID
	left join Invoice I on CD.ClockIndexID = I.ClockIndexID And I.CustomerID = @CustomerID
	
	Update @tblResult Set Amount= [dbo].[FuncGetElectricAmount] (@CustomerID ,KWHNumber)
		select * from @tblResult

END
GO
/****** Object:  StoredProcedure [dbo].[ProGetNewID]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ProGetNewID] 
	@TableName nvarchar(255)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	Declare @newID int
	if(@TableName = 'AccountObject')
	begin
		select @newID= max( AccountObjectID ) +1 from AccountObject
	end
		if(@TableName = 'Clock')
	begin
		select @newID = max(ClockID)+1 from Clock
	end
	select @newID as NewID
END
GO
/****** Object:  StoredProcedure [dbo].[ProInsertUpdateClockDetail]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ProInsertUpdateClockDetail]
	@ClockDetailID int,
	@ClockCode nvarchar(200),
	@ClockIndex int,
	@Year int,
	@Month int,
	@CreatedDate Date
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	Set nocount on
	Declare @ClockID int
	Declare @PreClockDetailID int;
	select @ClockID = IsNull(Clock.ClockID,-1) from Clock where Clock.ClockCode like @ClockCode
	Select @ClockDetailID = IsNull(ClockDetail.ClockIndexID,-1) from ClockDetail where @ClockDetailID = ClockDetail.ClockIndexID
	If(@ClockID>0)
	begin
	  If(@ClockDetailID<0)
	begin
		Select @ClockDetailID = max(ClockIndexID) +1 from ClockDetail;

		select top(1) @PreClockDetailID =  ClockIndexID from ClockDetail where ClockId = @ClockID and (( [Year] < @Year) or (@Year = [Year] and Month <@Month)) order by [Year] desc,[Month] desc
		Insert into ClockDetail
		(
			ClockID,
			ClockIndexID,
			[Month],
			[Year],
			CreatedDate,
			ClockIndex,
			PreClockIndexID

		)

		values
		(
			@ClockID,
			@ClockDetailID,
			@Month,
			@Year,
			@CreatedDate,
			@ClockIndex	,
			@PreClockDetailID
		)
		
	end
	else
	begin
		
		select top(1) @PreClockDetailID =  ClockIndexID from ClockDetail where ClockId = @ClockID and ( [Year] < @Year) or (@Year = [Year] and Month <@Month) order by [Year] desc,[Month] desc
	
		Update ClockDetail
		Set 
		ClockID = @ClockID,
			[Month] = @Month,
			[Year] = @Year,
			CreatedDate = @CreatedDate,
			ClockIndex= @ClockIndex,
			PreClockIndexID = @PreClockDetailID

		Where ClockDetail.ClockIndexID = @ClockDetailID
	end
	end
	select count(*) as RowAffacted from ClockDetail where ClockIndexID = @ClockDetailID
  
END
GO
/****** Object:  StoredProcedure [dbo].[ProInsertUpdateCustomer]    Script Date: 6/13/2020 8:40:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ProInsertUpdateCustomer]
	@AccountObjectID int,
	@AccountObjectCode nvarchar(200),
	@AccountObjectName nvarchar(200),
	@Address nvarchar(200),
	@PhoneNumber nvarchar(200),
	@ClockCode nvarchar(255),
	@IntVoltType int,
	@IntcontractType int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.

	Declare @CusID int,
			@ClockID int
	Select @CusID= AccountObject.AccountObjectID from AccountObject where @AccountObjectID = AccountObject.AccountObjectID
	Select top 1  @ClockID = IsNUll((C.ClockID),-1) from Clock C where C.ClockCode = @ClockCode
	
	Set @ClockID = ISNULL(@ClockID,-1)
	set @CusID = ISNULL(@CusID,-1)
	If(@ClockID<0)
	begin

		Select @ClockID = Max(ClockID) + 1 from Clock 
		insert into Clock
		(
			ClockID,
			ClockCode,
			ClockName
		)
		values
		(
			@ClockID,
			@ClockCode,	
			N'Đồng hồ ' + cast(@ClockID as nvarchar(255))
		)
		
	end 
	
	If(@CusID<0  )
	begin
		
		Select @AccountObjectID = max(AccountObjectID) +1 from AccountObject;

		Insert into AccountObject
		(
			AccountObjectID,
			AccountObjectCode,
			AccountAddress,
			AccountObjectName,
			AccountObjectType,
			ContractType,
			VoltageType,
			PhoneNumber,
			ClockID

		)

		values
		(
			@AccountObjectID,
			@AccountObjectCode,
			@Address,
			@AccountObjectName,
			0,
			@IntcontractType,
			Case when @IntcontractType = 0 then
				@IntVoltType
				else null
			end,
			@PhoneNumber,
			@ClockID
		)
		
	end
	else
	begin
	
		Update AccountObject
		Set
			AccountAddress = @Address,
			AccountObjectName = @AccountObjectName,
			VoltageType = @IntVoltType,
			PhoneNumber = @PhoneNumber
		Where AccountObjectID = @AccountObjectID
	end

	select count(*) as RowAffacted from AccountObject where AccountObjectID = @AccountObjectID
  
END
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Số điện thoại' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'PhoneNumber'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Tên' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'AccountObjectName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Địa chỉ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'AccountAddress'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'AccountObjectCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'AccountObjectID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'đồng hồ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'ClockID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0-khách hàng,1-Nhân viên' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'AccountObjectType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã hợp đồng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountObject', @level2type=N'COLUMN',@level2name=N'ContractType'
GO
USE [master]
GO
ALTER DATABASE [PTIT_SQA_EMS] SET  READ_WRITE 
GO
